package com.lvmama.dest.common.httpsqs4j.msg;

import java.io.Serializable;

/** 
 * @Title: HttpsqsMessage.java 
 * @Package com.lvmama.com.lvtu.common.httpsqs4j.msg 
 * @Description: TODO 
 * @author dengcheng 
 * @date 2015-4-16 上午11:57:48 
 * @version V1.0.0 
 */
public class HttpsqsMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long objectId;			// 对象ID
	private String objectType;		// 业务类型
	private String eventType;		// 消息类型
	private String addition; 		// 附加信息(无特定，可以灵活跟随信息)
	private String tag;
	
	public HttpsqsMessage (Long objectId,String objectType,String eventType,String tag,String addition){
		this.objectId = objectId;
		this.objectType = objectType;
		this.eventType = eventType;
		this.addition = addition;
		this.tag = tag;
	}
	
	public HttpsqsMessage (){
		
	}
	
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public String getEventType() {
		return eventType;
	}
	public String getAddition() {
		return addition;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public boolean isDestPersistanceLogEvent(){
		return objectType.equals(HttpsqsMessageFactory.OBJECT_TYPE.DEST_BU_LOG.name())&&eventType.equals(HttpsqsMessageFactory.EVENT_TYPE.DEST_LOG_EVENT.name());
	}
	
	public boolean isDestExceptionLogEvent(){
		return objectType.equals(HttpsqsMessageFactory.OBJECT_TYPE.DEST_BU_LOG.name())&&eventType.equals(HttpsqsMessageFactory.EVENT_TYPE.DEST_EXCEPTION_LOG_EVENT.name());
	}
}
