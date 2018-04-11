/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. */
package com.lvmama.xcl.hold.pub.vo;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lvmama.xcl.hold.pub.SqlAnalyzer;



public class DBWriteRecord implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final String VIRTUAL_TABLE_NAME="DBWriteRecord.VIRTUAL_TABLE_NAME";
	private List<TableWriteRecord> writeRecords=new LinkedList<TableWriteRecord>();
	public List<TableWriteRecord> getWriteRecords() {
		return writeRecords;
	}
	public void setWriteRecords(List<TableWriteRecord> writeRecords) {
		this.writeRecords = writeRecords;
	}
	
	public void updated(String tableName){
		if(tableName==null||tableName.length()==0){
			updated(VIRTUAL_TABLE_NAME);
			return;
		}
		
		for(TableWriteRecord tableWriteRecord:writeRecords){
			if(isSameTable(tableName, tableWriteRecord)){
				tableWriteRecord.update();
				return;
			}
		}
		
		writeRecords.add(new TableWriteRecord(SqlAnalyzer.getPureTableName(tableName),SqlAnalyzer.getSchema(tableName)));
	}
	
	public boolean isUpdatedIn(Long second){
		if(second==null){
			return true;
		}
		Long now=System.currentTimeMillis();
		Date since=new Date(now-second*1000);
		return isUpdatedSince(since);
	}
	
	public boolean isUpdatedSince(Date since){
		return isUpdatedSince(null,since);
	}
	
	public boolean isUpdatedSince(String tableName,Date since){
		if(writeRecords==null||writeRecords.size()==0){
			return false;
		}
		
		for(TableWriteRecord tableWriteRecord:writeRecords){
			Date recordLastestWriteTime = tableWriteRecord.getLastestWriteTime();
			
			if(tableName==null||isSameTable(tableName, tableWriteRecord)){
				if(recordLastestWriteTime!=null&&(since==null||recordLastestWriteTime.after(since))){
					return true;
				}
			}
		}
		
		return false;
	}
	private boolean isSameTable(String tableName,
			TableWriteRecord tableWriteRecord) {
		String schema1=SqlAnalyzer.getSchema(tableName);
		String table1=SqlAnalyzer.getPureTableName(tableName);
		
		String schema2=tableWriteRecord.getSchema();
		String table2=tableWriteRecord.getTableName();
		
		if(schema1!=null&&!"".equals(schema1)&&schema2!=null&&!"".equals(schema2)){
			if(!schema1.equalsIgnoreCase(schema2)){
				return false;
			}
		}
		
		return table1.equalsIgnoreCase(table2);
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(TableWriteRecord record:writeRecords){
			sb.append(record.getSchema()==null?"":record.getSchema());
		    sb.append("."+record.getTableName());
			sb.append("-");
			sb.append(record.getLastestWriteTime());
			sb.append(" ");
		}
		return sb.toString();
	}
	
}
