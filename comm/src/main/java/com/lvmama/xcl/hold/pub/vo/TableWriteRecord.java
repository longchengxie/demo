/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold.pub.vo;
import java.io.Serializable;
import java.util.Date;

public class TableWriteRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String schema;
	private String tableName;
	private Date lastestWriteTime;
	
	public TableWriteRecord(){}
	
	public TableWriteRecord(String tableName,String schema){
		this.tableName=tableName;
		this.schema=schema;
		this.lastestWriteTime=new Date();
	}
	
	public TableWriteRecord(String tableName,String schema, Date lastestWriteTime){
		this.tableName=tableName;
		this.schema=schema;
		this.lastestWriteTime=lastestWriteTime;
	}
	
	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getLastestWriteTime() {
		return lastestWriteTime;
	}

	public void setLastestWriteTime(Date lastestWriteTime) {
		this.lastestWriteTime = lastestWriteTime;
	}

	public void update(){
		this.lastestWriteTime=new Date();
	}
}
