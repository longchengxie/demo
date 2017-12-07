package com.lvmama.xcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvmama.xcl.service.FreemarkerService;


@Controller
@RequestMapping("freemarker")
public class FreemarkerController {
	@Autowired
	private FreemarkerService freemarkerService;

	@RequestMapping(value = "/showContract",produces="text/html;charset=UTF-8")
	@ResponseBody()
	public String showContract() {
		String html = freemarkerService.getContractTemplateHtml();
		return html;
	}
}
