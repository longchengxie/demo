/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold.pub;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lvmama.xcl.hold.pub.vo.DBWriteRecord;
import com.lvmama.xcl.hold.pub.vo.TableWriteRecord;

import net.sf.json.JSONObject;


public class DistributedContext {
	//please refer to the constants in class DistributedContextConstant.java for the key of this map 
	private static final ThreadLocal<Map<String,Serializable>> context=new ThreadLocal<Map<String,Serializable>>();
	
	public static void putToContext(Map<String,Serializable> map){
		if(map==null||map.isEmpty()){
			return;
		}
		getContext().putAll(map);
	}
	
	public static Map<String,Serializable> getContext(){
		if(context.get()==null){
			init();
		}
		
		return context.get();
	}
	
	public static void init(){
		HashMap<String, Serializable> map = new HashMap<String,Serializable>();
		map.put(DistributedContextConstant.CONTEXT_KEY_DBWRITERECORD, new DBWriteRecord());
		
		context.set(map);
	}
	
	public static void remove(){
		context.remove();
	}
	
	public static Serializable get(String key){
		return getContext().get(key);
	}
	
	public static void put(String key, Serializable value){
		getContext().put(key, value);
	}
	
	public static void remove(String key){
		getContext().remove(key);
	}
	
	public static DBWriteRecord getDBWriteRecord(){
		return (DBWriteRecord)getContext().get(DistributedContextConstant.CONTEXT_KEY_DBWRITERECORD);
	}
	
	public static String toJSON(){
		if(context==null){
			return "";
		}
		
		try{
			return JSONObject.fromObject(getContext()).toString();			
		}catch(Exception e){
			return "";
		}
	}
	
	public static Map<String,Serializable> toObject(String jsonString){
		if(jsonString==null||jsonString.trim().length()==0){
			return new HashMap<String,Serializable>();
		}
		
		try{
			Map<String, Class> childMap=new HashMap<String, Class>();
			childMap.put(DistributedContextConstant.CONTEXT_KEY_DBWRITERECORD, DBWriteRecord.class);
			childMap.put("writeRecords", TableWriteRecord.class);
			
			Map<String,Serializable> pojo;
			
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			if(childMap == null || childMap.isEmpty())
				pojo = (Map<String,Serializable>)JSONObject.toBean(jsonObject, Map.class);
			else
				pojo = (Map<String,Serializable>)JSONObject.toBean(jsonObject, Map.class, childMap);
			return pojo;
		}catch(Exception e){
			return new HashMap<String,Serializable>();
		}
	}
	
	public static boolean isLvmamaHessian(){
		if(get("isLvmamaHessian")!=null&&"Y".equalsIgnoreCase(get("isLvmamaHessian").toString())){
			return true;
		}
		return false;
	}
	
	public static void setLvmamaHessian(){
		put("isLvmamaHessian", "Y");
	}
	
	public static void main(String args[]){
		DBWriteRecord dbWriteRecord=new DBWriteRecord();
		dbWriteRecord.updated(null);
		dbWriteRecord.updated("perm_user");
		
		DistributedContext.put(DistributedContextConstant.CONTEXT_KEY_DBWRITERECORD, dbWriteRecord);
		
		System.out.println(DistributedContext.getContext());
		
		String jsonString = toJSON();
//		System.out.println(jsonString);
		
//		System.out.println("toObject1:"+toObject(null));
//		System.out.println("toObject2:"+toObject(""));
		DistributedContext.putToContext(DistributedContext.toObject(null));       
		DistributedContext.putToContext(DistributedContext.toObject(""));
		System.out.println(DistributedContext.toObject(jsonString));
	}
}
