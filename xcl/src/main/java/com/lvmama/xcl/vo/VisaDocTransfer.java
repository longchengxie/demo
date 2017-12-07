package com.lvmama.xcl.vo;

import java.io.Serializable;


public class VisaDocTransfer implements Serializable {

	private static final long serialVersionUID = -5023139833232747621L;
	
	private int serialNumber;//序号
	
	private Long orderId;//订单ID
	
	private String productName; //产品名称
	
	private String name; //游玩人姓名
	
	private String userName;//下单人名字
	
	private String collectTime; //收取材料时间
	
	private String collectUser; //收取材料员工号
	
	private String collectDocDetail; //收取材料员工号
	
	private Long batchNo;//批次号
	
	private String exportTime;//导出时间

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	public String getCollectUser() {
		return collectUser;
	}

	public void setCollectUser(String collectUser) {
		this.collectUser = collectUser;
	}

	public String getCollectDocDetail() {
		return collectDocDetail;
	}

	public void setCollectDocDetail(String collectDocDetail) {
		this.collectDocDetail = collectDocDetail;
	}

	public Long getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}

	public String getExportTime() {
		return exportTime;
	}

	public void setExportTime(String exportTime) {
		this.exportTime = exportTime;
	}
	
}
