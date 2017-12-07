package com.lvmama.xcl.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

public class MemcachedUtil2 {
	private static Object LOCK = new Object();
	private static Object SESSION_LOCK = new Object();
	private final static  Log log = LogFactory.getLog(MemcachedUtil2.class);
	private Properties properties;
	private MemCachedClient memCachedClient;
	private MemCachedClient sessionMemCachedClient;
	public final static int ONE_HOUR=3600;
	public final static int TWO_HOUR=7200;
	public final static String memcacheMapKey="MEMACACHE_MAP_KEY";
	
	private static MemcachedUtil2 instance;
	
	//distribution lock
	private static final int DEFAULT_LOCK_SECCONDS=5;
	private static final int DEFAULT_TRYLOCK_TIMEOUT_SECONDS=5;

	private final static String CACHE_SERVER = "cache.server";
	private final static String SESSION_CACHE_SERVER = "session.cache.server";
	private final static String CACHE_ENABLE = "cache.enable";

	private boolean isSweet = true;

	private void init() {
		try {
			String serverStr = getProperty(CACHE_SERVER);
			if (StringUtils.isBlank(serverStr)) {
				log.warn("sweet config load memcached is failed");
				isSweet = false;

				properties = new Properties();
				Resource resource = new ClassPathResource("memcached.properties");
				properties.load(resource.getInputStream());

				serverStr = getProperty(CACHE_SERVER);
			}
			//数据缓存服务器，“,”表示配置多个memcached服务
			String[] servers = serverStr.replaceAll(" ", "").split(",");

			SockIOPool pool = SockIOPool.getInstance("dataServer");

			pool.setServers(servers);
			pool.setFailover(true);
			pool.setInitConn(10);
			pool.setMinConn(5);
			pool.setMaxConn(50);
			pool.setMaintSleep(30);
			pool.setNagle(false);
			pool.setSocketTO(30000);
			pool.setBufferSize(1024*1024*5);
			pool.setAliveCheck(true);
			pool.initialize(); /* 建立MemcachedClient实例 */
			memCachedClient = new MemCachedClient("dataServer");
		} catch (IOException e) {
			log.error(e);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
	}
	private String getProperty(String clusterName) {
		String prop = null;
		if (isSweet) {
			try {
				//prop = ZooKeeperConfigProperties.getProperties(clusterName);
			} catch (Throwable t) {
				log.warn(t);
			}
		} else {
			prop = properties.getProperty(clusterName);
		}
		return prop;
	}
	private void initSessionMemCached() {
		try {
			String serverStr = getProperty(SESSION_CACHE_SERVER);
			if (StringUtils.isBlank(serverStr)) {
				log.warn("sweet config load session memcached is failed");
				isSweet = false;

				properties = new Properties();
				Resource resource = new ClassPathResource("memcached.properties");
				properties.load(resource.getInputStream());

				serverStr = getProperty(SESSION_CACHE_SERVER);
			}
			//数据缓存服务器，“,”表示配置多个memcached服务
			String[] sessionServers = serverStr.replaceAll(" ", "").split(",");

			SockIOPool sessionPool = SockIOPool.getInstance("sessionServer");

			sessionPool.setServers(sessionServers);
			sessionPool.setFailover(true);
			sessionPool.setInitConn(10);
			sessionPool.setMinConn(5);
			sessionPool.setMaxConn(50);
			sessionPool.setMaintSleep(30);
			sessionPool.setNagle(false);
			sessionPool.setSocketTO(30000);
			sessionPool.setBufferSize(1024*1024*5);
			sessionPool.setAliveCheck(true);
			sessionPool.initialize(); /* 建立MemcachedClient实例 */
			sessionMemCachedClient = new MemCachedClient("sessionServer");
		} catch (IOException e) {
			log.error(e);
		}
		catch (Exception ex) {
			log.error(ex, ex);
		}
	}
	
	private MemcachedUtil2(){
		init();
	}
	
	private boolean isCacheEnabled() {
		boolean useCache = false;
		try {
			useCache = Boolean.valueOf(getProperty(CACHE_ENABLE));
		} catch (Exception e) {
			useCache = false;
			log.info("Please enable memcached");
		}
		return useCache;
	}
	
	/**
	 * 改用嵌套类静态实始化
	 * @return
	 */
	public static MemcachedUtil2 getInstance() {
		if(instance == null){
			synchronized(LOCK) {
				if (instance==null) {
					instance=new MemcachedUtil2();
				}
			}
		}
		return instance;
	}

	private MemCachedClient getMemCachedClient(boolean isForSession) {
		if(isForSession){
			if(sessionMemCachedClient == null){
				synchronized(SESSION_LOCK) {
					if (sessionMemCachedClient==null) {
						initSessionMemCached();
					}
				}
			}
			return sessionMemCachedClient;
		}
		else
			return memCachedClient;
	}
	
	public boolean replace(String key, int seconds, Object obj) {
		return replace(key, seconds, obj,false);
	}
	/**
	 * 替换
	 * @param key
	 * @param seconds 过期秒数
	 * @param obj
	 * @return
	 */
	public boolean replace(String key, int seconds, Object obj,boolean isForSession) {
		if(StringUtils.isEmpty(key)){
			return false;
		}
		if(obj==null){
			return true;
		}
		try{
			if (isCacheEnabled()) {
				Date  expDate = getDateAfter(seconds);
				boolean result = getMemCachedClient(isForSession).replace(key, obj, expDate);
				if(log.isDebugEnabled()){
					log.debug("SET A OBJECT: KEY:" + key + ", OBJ:" + obj + ", exp:" + expDate  + ", result:" + result);
				}
				return result;
			}
			return true;
		}catch(Exception e) {
			log.error(e);
		}
		return false;
	}

	/**
	 * 放
	 * @param key
	 * @param seconds 过期秒数
	 * @param obj
	 * @return
	 */
	public boolean set(String key, int seconds, Object obj,boolean isForSession) {
		return set(key, getDateAfter(seconds), obj,isForSession);
	}
	
	public boolean set(String key, int seconds, Object obj) {
		return set(key, getDateAfter(seconds), obj,false);
	}

	
	/**
	 * 将KEY保存到memcache中
	 * 
	 * @param key
	 * @param exp
	 * @param obj
	 * @return
	 */
	public boolean set(String key,Date exp,Object obj,boolean isForSession){
		if(StringUtils.isEmpty(key)){
			return false;
		}
		if(obj==null){
			return true;
		}
		try{
			if (isCacheEnabled()) {
				boolean result = getMemCachedClient(isForSession).set(key, obj, exp);
				if(log.isDebugEnabled()){
					log.debug("SET A OBJECT: KEY:" + key + ", OBJ:" + obj + ", exp:" + exp + ", result:" + result);
				}
				return result;
			}
			return true;
		}catch(Exception e) {
			log.error(e);
		}
		return false;
	}
	
	public boolean set(String key,Date exp,Object obj){
		return set(key,exp,obj,false);
	}
	
	/**
	 * @deprecated 此方法严重影响流量，应该被废弃 <a>
	 * @author Brian
	 * 
	 * 保存key和描述信息；
	 * 
	 * @param key
	 * @param second （单位：秒）
	 * @param obj
	 * @return
	 */
	public boolean setWithDis(String key,int seconds,Object obj,String discript){
		if(StringUtils.isEmpty(key)){
			return false;
		}
		if(obj==null){
			return true;
		}
		try{
			if (isCacheEnabled()) {
				Date expDate = getDateAfter(seconds);
				//this.putKeyDisMap(key,discript);
				boolean result = memCachedClient.set(key, obj, expDate);
				if(log.isDebugEnabled()){
					log.debug("SET A OBJECT: KEY:" + key + ", OBJ:" + obj + ", expDate:" + expDate) ;
				}
				return result;
			}
			return true;
		}catch(Exception e) {
			log.error(e);
		}
		return false;
	}
	
	/**
	 * 把相应的Key值和描述存到此方法中；
	 * 
	 * @param key
	 * @param discript
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean putKeyDisMap(String key,String discript) {
		Map<String,String> memMap ;
		Object obj = memCachedClient.get(memcacheMapKey);
		if(obj == null) {
			memMap = new HashMap<String, String>();
		} else {
			memMap = (HashMap<String, String>) obj;
		}
		memMap.put(key, discript);
		memCachedClient.set(memcacheMapKey,memMap,getDateAfter(60*60*48));
		
		return true;
	}
	
	/**
	 * 放
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean set(String key, Object obj) {
		return set(key,ONE_HOUR,obj);
	}
	
	/**
	 * 取
	 * @param key
	 * @return
	 */
	public Object get(String key,boolean isForSession) {
		try{
			if (isCacheEnabled()) {
				Object obj = getMemCachedClient(isForSession).get(key);
				if(log.isDebugEnabled()){
					log.debug("GET A OBJECT: KEY:" + key + " OBJ:" + obj) ;
				}
				return obj;
			}
		}catch(Exception e) {
			log.error(e);
		}
		return null;
	}
	
	public Object get(String key) {
		return get(key,false);
	}
	
	/**
	 * 取
	 * @param key
	 * @return
	 */
	public boolean remove(String key,boolean isForSession) {
		if(StringUtils.isEmpty(key)){
			return false;
		}
		try{
			if (isCacheEnabled()) {
				log.info("delete memcached key: " + key);
				return getMemCachedClient(isForSession).delete(key);
			}
		}catch(Exception e) {
			log.error(e);
		}
		return true;
	}
	public boolean remove(String key) {
		return remove(key,false);
	}
	
    /**
     * 获得当前开始活参数秒的时间日期
    * @Title: getDateAfter
    * @Description:
    * @param
    * @return Date    返回类型
    * @throws
     */
    public static Date getDateAfter(int second) {
        Calendar cal = Calendar.getInstance();
 		cal.add(Calendar.SECOND, second);
 		return cal.getTime();
 	}
	
    
    // memcached  incr/decr 原子操作 
    /**
     * 计数加
     * incr命令语法为incr key integer 即将指定主键key的value值加上给定的integer，默认为1
     * @param key
     * @param isForSession
     * @return obj
     */
    public Object incr(String key) {
		try{
			if (isCacheEnabled()) {
				Object obj = getMemCachedClient(false).incr(key);
				if(log.isDebugEnabled()){
					log.debug("incr A OBJECT: KEY:" + key + " OBJ:" + obj) ;
				}
				return obj;
			}
		}catch(Exception e) {
			log.error(e);
		}
		return -1;
	}
    
    /**
     * 计数减 
     * decr命令语法为decr key interger，即将指定主键key的value值减去给定的interger。
     * @param key
     * @param isForSession
     * @return
     */
    public Object decr(String key) {
		try{
			if (isCacheEnabled()) {
				Object obj = getMemCachedClient(false).decr(key);
				if(log.isDebugEnabled()){
					log.debug("incr A OBJECT: KEY:" + key + " OBJ:" + obj) ;
				}
				return obj;
			}
		}catch(Exception e) {
			log.error(e);
		}
		return -1;
	}
    
    /**
     * 存储key的计数器，值为count
     * @param key
     * @param count
     * @return
     */
    public long addOrIncr(String key,long count) {
		try{
			return getMemCachedClient(false).addOrIncr(key,count);
		} catch (Exception e) {
			log.error(e);
		}
		return 0l;
	}
    
    /**
     * 初始化计数器；
     * @param key
     * @param count
     * @return
     */
    public long addOrIncrAndInit(String key,long count) {
    	try{
    		getMemCachedClient(false).delete(key);
			return getMemCachedClient(false).addOrIncr(key,count);
		}catch(Exception e) {
			log.error(e);
		}
		return 0l;
    }
    
    /**
     * 获取计数器值 
     * 获取key的计数器，如果不存在返回-1。
     * @param key
     * @param count
     * @return
     */
    public long getCounter(String key) {
		try{
			return getMemCachedClient(false).getCounter(key);
		}catch(Exception e) {
			log.error(e);
		}
		return -1;
	}
    
    public boolean tryLock(String lockKey){
    	return tryLock(lockKey,DEFAULT_LOCK_SECCONDS,DEFAULT_TRYLOCK_TIMEOUT_SECONDS);
    }
    
    public boolean tryLock(String lockKey, int lockSec, int timeOutSec){
    	MemCachedClient client=getMemCachedClient(false);
    	
    	long start=System.currentTimeMillis();
    	while(true){
    		boolean locked=client.add(lockKey, "", MemcachedCalendarUtil.getDateAfter(lockSec));
    		if(locked){
    			return true;
    		}else{
    			long now=System.currentTimeMillis();
    			long costed = now-start;
				if(costed>=timeOutSec*1000){
					return false;
				}
    		}
    	}
    }

	/**
	 * 该锁是否已被持有 false:未被持有，true：已被持有
	 * @param lockKey
	 * @param lockSec
	 * @return
	 */
	public boolean isHoldLock(String lockKey, int lockSec){
		boolean isHoldLock = false;
		MemCachedClient client = getMemCachedClient(false);
		boolean locked = client.add(lockKey, "holded", MemcachedCalendarUtil.getDateAfter(lockSec));
		if(!locked){
			isHoldLock = true;
		}
		return isHoldLock;
	}


    public boolean releaseLock(String lockKey){
    	MemCachedClient client=getMemCachedClient(false);
    	return client.delete(lockKey);
    }
    
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		Map map = (Map) MemcachedUtil2.getInstance().get("MEM_TEST_KEY_LL_6");
		if(map == null){
			map = new HashMap();
		}

		for(int i=0;i<1;i++) {
			Random r =  new Random(System.currentTimeMillis());
			String key = "KEY_" + r.nextLong();
			System.out.println("keys: " +key);

			byte[] bt = new byte[0];
			String value= new String(bt,"UTF-8");
			for(int j=0;j<10000;j++) {
				value= value + "qweqweqweqweqweqwewequyrqwieurpasjdflkasdfasdrwqioeurpqwerqweqwertyuiopqwertyuiopqwertyuiopqwertqqqq";
			}
			//System.out.println(key + " : " + MemcachedUtil.getInstance().get(key));
			map.put(key, value);
		}
		
		boolean a = MemcachedUtil2.getInstance().set("MEM_TEST_KEY_LL_6", map);
		
		boolean b = MemcachedUtil2.getInstance().set("MEM_TEST_KEY_LL_2", "2");

		
		System.out.println("keys: " + map.size());
		
		System.out.println(a + ", " + b);

	}
}
