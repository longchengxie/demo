package com.lvmama.xcl.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2805778420945073358L;

	private Long id;
	private String name;
	private List<String> list;
	private String text;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birstaryDay;
	
	
	public Date getBirstaryDay() {
		return birstaryDay;
	}
	public void setBirstaryDay(Date birstaryDay) {
		this.birstaryDay = birstaryDay;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
