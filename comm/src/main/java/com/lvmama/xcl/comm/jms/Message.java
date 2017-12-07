package com.lvmama.xcl.comm.jms;

import java.io.Serializable;

import lombok.Data;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1118236567096207803L;

	private Long objectId;
	private String objectType;
	private String eventType;
	private String addition; // 无特定，可以灵活跟随信息
	private String operatorName;
	private Long createMilTime; // 消息创建时间（毫秒数）

	@SuppressWarnings("unused")
	private Message() {
	}

	protected Message(Long objectId, String objectType, String eventType) {
		this.objectId = objectId;
		this.objectType = objectType;
		this.eventType = eventType;
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

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public boolean isNullAddition() {
		return addition == null;
	}

	public String getAddition() {
		return addition;
	}

	public void setAddition(String addition) {
		this.addition = addition;
	}
	

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Message) {
			Message cc = (Message) obj;
			return objectId.equals(cc.getObjectId()) && eventType.equals(cc.getEventType());
		} else {
			return false;
		}
	}
	
	public Long getCreateMilTime() {
		return createMilTime;
	}

	public void setCreateMilTime(Long createMilTime) {
		this.createMilTime = createMilTime;
	}

	@Override
	public String toString() {
		return "Message [objectId=" + objectId + ", objectType=" + objectType + ", eventType=" + eventType + ", addition=" + addition + ", operatorName=" + operatorName
				+ ", createMilTime=" + createMilTime + "]";
	}

}
