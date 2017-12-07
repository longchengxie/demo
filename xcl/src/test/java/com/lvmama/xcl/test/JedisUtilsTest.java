package com.lvmama.xcl.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.lvmama.xcl.redisCluster.RedisClusterClient;
import com.lvmama.xcl.utils.BeanHessionSerializeUtil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-xcl-beans.xml"})
public class JedisUtilsTest implements Serializable{

	static String[] keyList = new String[26];
	static {
		for(int i = 0; i < 26; i++){
			keyList[i] = (char)(97+i)+"";
		}
	}
	@Autowired
	private RedisClusterClient jedisCluster;
	
	@Autowired
	private com.lvmama.xcl.redisShard.RedisService redisService;

	@Test
	public void test(){
		for(;true;) {
			long now1 = System.currentTimeMillis();
			for(String key :new String[]{"a","b","c"}) {//a8 b6 c7
				try {
					System.out.print(key+"->");
					long now = System.currentTimeMillis();
					//获取实例报错，因为 集群中至少应该有奇数个节点，所以至少有三个节点，每个节点至少有一个备份节点，所以至少要有6节点（主节点、备份节点由redis-cluster集群确定）。
					String obj = jedisCluster.getJedisCluster().set(key,"-"+key+"-");
					System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			System.out.println("---------------------------------------------------"+(System.currentTimeMillis()-now1));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test2() {
		for(String key :keyList) {
			try {
				System.out.print(key+"->");
				long now = System.currentTimeMillis();
				String obj = jedisCluster.getJedisCluster().set(key,"-"+key+"-");
				System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
    public static void main(String[] args) {  
    	//testArray();
        //jedisClusterNodesTest1();  
    	//jedisNormal();
    	jedisNormal2();
    	//jedisShardSimplePool();
    }
static class A implements Serializable{
	
	public A() {
		String a = "abc";
		this.a=  "input ....."+a;
		System.out.println("a create................");
		map.put(1L, a);
	}
	Map<Long, String> map = new HashMap<Long, String>();
	String a = "aaaaaaaaaa";
	int i =  10;
	public Map<Long, String> getMap() {
		return map;
	}
	public void setMap(Map<Long, String> map) {
		this.map = map;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
}
    public static void testArray(){
    	byte[] b = BeanHessionSerializeUtil.serialize(new ArrayList());
    	System.out.println(b.length);
    	String[] a = new String[]{"a","b"};
    	List<String> array = new ArrayList<String>();
    	array.add("a");
    	array.add("a1");
    	array.add("a2");
    	array.add("a3");
    	A aaa = new A();
    	aaa.map.put(1L, "111111");
    	aaa.map.put(2L, "2222222");
    	String by = JSON.toJSONString(aaa);
		System.out.println(by);
		A aa = JSON.parseObject(by, A.class);
    	System.out.println(aa.a + "       --         " + aa.map);
    }
    
    /**
     * 支持集群,redis3.0之前服务端不支持集群，所以只能通过jedisShard实现客户端集群
     * 节点要求有1个以上就行
     */
	public static void jedisShardSimplePool() {
		List<JedisShardInfo> shards = Arrays.asList(
				new JedisShardInfo("192.168.0.246", 6309), 
				new JedisShardInfo("192.168.0.246", 6319), 
				new JedisShardInfo("192.168.0.246", 6329),
				new JedisShardInfo("192.168.0.246", 6369), 
				new JedisShardInfo("192.168.0.246", 6379), 
				new JedisShardInfo("192.168.0.246", 6389));

		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
		ShardedJedis one = pool.getResource();
		for (String key : keyList) {
			try {
				System.out.print(key + "->");
				long now = System.currentTimeMillis();
				String obj = one.set(key, "-" + key + "-");
				System.out.println("内容：【" + obj + "】时间：" + (System.currentTimeMillis() - now));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pool.returnResource(one);
		pool.destroy();
	}
	
	@Test
	public void testRediaShard(){
		redisService.set("fuck", "ssssssssss");
	}
    /**
     * 不支持集群
     * 单点
     */
    private static void jedisNormal() {
        Jedis jedis = new Jedis("192.168.0.246", 6379);
        for(String key :keyList) {
        	try {
				System.out.print(key+"->");
				long now = System.currentTimeMillis();
				String obj = jedis.set(key,"-"+key+"-");
				System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        jedis.disconnect();
        jedis.close();
    }
    /**
     * 不支持集群
     * 单点
     */
    private static void jedisNormal2() {
    	Jedis jedis = new Jedis("127.0.0.1", 6379);
		try {
			long now = System.currentTimeMillis();
			String obj = jedis.setex("pojo".getBytes(),60*10,BeanHessionSerializeUtil.serialize(new JedisUtilsTest().new TestA(1L,"aa")));
			byte[] bs = jedis.get("pojo".getBytes());
			System.out.println((TestA)BeanHessionSerializeUtil.deserialize(bs));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	jedis.disconnect();
    	jedis.close();
    }

    /**
     * 支持集群
     */
	private static void jedisClusterNodesTest() {
		JedisCluster jedisCluster = getJedis();  
        try {  
            for(String key :keyList) {
    			try {
    				System.out.print(key+"->");
    				long now = System.currentTimeMillis();
    				String obj = jedisCluster.set(key,"-"+key+"-");
    				System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    		}
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {
				jedisCluster.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
        }
	}
	class TestA implements Serializable{
		Long a;
		String b;
		public TestA(Long a, String b ) {
			this.a = a;
			this.b= b;
		}
		@Override
		public String toString() {
			return "TestA [a=" + a + ", b=" + b + "]";
		}
		
	}
	private static void jedisClusterNodesTest1() {
		System.out.println(Long.class.isPrimitive());
		System.out.println(int.class.isPrimitive());
		byte[] a = BeanHessionSerializeUtil.serialize(11111);
		System.out.println(Arrays.toString(a));
		Integer b = 0;
		BeanHessionSerializeUtil.deserialize(a);
		byte[] a1 = BeanHessionSerializeUtil.serialize(a);
		System.out.println(a1);
		
		byte[] o = BeanHessionSerializeUtil.serialize(new JedisUtilsTest().new TestA(1L,"aa"));
		System.out.println(BeanHessionSerializeUtil.deserialize(o));
	}
	/**
	 * 支持集群
	 */
	private static void jedisClusterNodesTest4() {
		JedisCluster jedisCluster = getJedis();  
		try {  
			long now = System.currentTimeMillis();
			
			jedisCluster.del("b");
			Map<String, String> map = new HashMap<String, String>();
			map.put("1", "12");
			map.put("2", "22");
			map.put("3", "32");
			jedisCluster.hmset("b", map);
			Object obj1 = jedisCluster.hgetAll("b");
			System.out.println("内容：【"+obj1+"】时间："+(System.currentTimeMillis()-now));
			
			jedisCluster.del("a");
			jedisCluster.hset("a", "1", "fancy~");
			jedisCluster.hset("a", "2", "30~");
			Object obj = jedisCluster.hgetAll("a");
			System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
//			jedisCluster.decr("a");
//			jedisCluster.incr("a");
//			obj = jedisCluster.get("a");
//			System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			try {
				jedisCluster.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	/**
	 * 支持集群
	 */
	private static void jedisClusterNodesTest3() {
		JedisCluster jedisCluster = getJedis();  
		try {  
			long now = System.currentTimeMillis();
			jedisCluster.setex("a", 2, "00000000000000000000");
			Thread.sleep(1100);
			String obj = jedisCluster.get("a");
			System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			try {
				jedisCluster.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	/**
	 * 支持集群，redis3.0后服务端支持集群，所以用jedisCluster
	 */
	private static void jedisClusterNodesTest2() {
		JedisCluster jedisCluster = getJedis();  
		try {  
			long now = System.currentTimeMillis();
			jedisCluster.set("", "00000000000000000000");
			
			String obj = jedisCluster.get("");
			System.out.println("内容：【"+obj+"】时间："+(System.currentTimeMillis()-now));
		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			try {
				jedisCluster.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	private static JedisCluster getJedis() {
		JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(100);  
        config.setMaxIdle(100);  
        config.setMinIdle(100);  
        config.setMaxWaitMillis(6 * 1000);  
        config.setTestOnBorrow(true);  
        //集群中至少应该有奇数个节点，所以至少有三个节点，每个节点至少有一个备份节点，所以至少要有6节点（主节点、备份节点由redis-cluster集群确定）。
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();  
        jedisClusterNodes.add(new HostAndPort("192.168.0.246", 6309));  
        jedisClusterNodes.add(new HostAndPort("192.168.0.246", 6319));  
        jedisClusterNodes.add(new HostAndPort("192.168.0.246", 6329));  
        jedisClusterNodes.add(new HostAndPort("192.168.0.246", 6369));  
        jedisClusterNodes.add(new HostAndPort("192.168.0.246", 6379));  
        jedisClusterNodes.add(new HostAndPort("192.168.0.246", 6389));  
  
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100, config);
		return jedisCluster;
	}  

}
