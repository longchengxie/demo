package com.lvmama.xcl.comm;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**
 * 操作日志实体类
 * 
 * @author wenzhengtao
 *
 */
@Data
public class ComLog implements Serializable {
	
	private static final long serialVersionUID = 1500161944413261340L;
	
	private String msgId;
	
	private String sysName;
	
	private String tableName;
	
	private Long logId;

	private Date createTime;
	
	private Date receiveTime;
	
	private Date logTime;

	private String content;

	private String logType;

	private String logName;

	private String memo;

	private String operatorName;

	private String objectType;

	private Long objectId;

	private Long parentId;

	private String parentType;

	private String contentType;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Override
	public String toString() {
		return "ComLog [logId=" + logId + ", createTime=" + createTime
				+ ", content=" + content + ", logType=" + logType
				+ ", logName=" + logName + ", memo=" + memo + ", operatorName="
				+ operatorName + ", objectType=" + objectType + ", objectId="
				+ objectId + ", parentId=" + parentId + ", parentType="
				+ parentType + ", contentType=" + contentType + "]";
	}
}