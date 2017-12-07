package com.lvmama.xcl.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PermUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 388701923630131495L;
	private Long userId;
	private Long departmentId;
	private String userName;
	private String realName;
	private String mobile;
	private String dataLimited;
	private String valid;
	private String password;
	private String employeeNum;
	private String position;
	private String departmentName;
	private Boolean permUserPass;
	private Boolean isChecked=false;
	private String extensionNumber;
	private String isHuaweiCc;
	
	private Long permissionId;
	private String status;
	private Integer crCount;
	
	private String workStatus;
	

	/**
	 * 人员邮箱
	 * updater : 尚正元
	 * update_date: 2012-04-19
	 */
	private String email;
	/**
	 * 是否有组织权限(Y:是,N:否)
	 */
	private String isOrgPerm;
	//VST组织权限ID集合
	private Collection<Long> vstOrgIdList;
	
	public PermUser() {

	}

	public PermUser(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDataLimited() {
		return dataLimited;
	}

	public void setDataLimited(String dataLimited) {
		this.dataLimited = dataLimited;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}


	public Boolean getPermUserPass() {
		return permUserPass;
	}

	public void setPermUserPass(Boolean permUserPass) {
		this.permUserPass = permUserPass;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	



	public String getZhValid() {
		return this.valid.equals("Y") ? "正常" : "锁定";
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}


	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	public String getUserInfoStr(){
		return "用户ID：" + userId 
				+ "；用户名：" + userName
				+ "；姓名：" + realName
				+ "；部门编号：" + departmentId
				+ "；是否有效：" + valid
				+ "；职务：" + position
				+ "；是否为华为客服系统账号：" + isHuaweiCc
				;
	}

	public String getIsHuaweiCc() {
		return isHuaweiCc;
	}

	public void setIsHuaweiCc(String isHuaweiCc) {
		this.isHuaweiCc = isHuaweiCc;
	}

	public Integer getCrCount() {
		return crCount;
	}

	public void setCrCount(Integer crCount) {
		this.crCount = crCount;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getIsOrgPerm() {
		return isOrgPerm;
	}

	public void setIsOrgPerm(String isOrgPerm) {
		this.isOrgPerm = isOrgPerm;
	}

	public Collection<Long> getVstOrgIdList() {
		return vstOrgIdList;
	}

	public void setVstOrgIdList(Collection<Long> vstOrgIdList) {
		this.vstOrgIdList = vstOrgIdList;
	}

	
}
