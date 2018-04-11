package com.lvmama.xcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvmama.xcl.service.DataSourceService;

@Controller
@RequestMapping("/dataSource")
public class DataSourceController {
	
	@Autowired
	private DataSourceService dataSourceService;
	
	@ResponseBody
	@RequestMapping("/data")
	public Object dataSource(){
		dataSourceService.dataSource();
		return "fuck";
	}

}
