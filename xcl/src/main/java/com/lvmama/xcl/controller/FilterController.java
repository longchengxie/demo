package com.lvmama.xcl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 测试过滤器匹配规则   /*   /filter/aa/*
 * @author xiechenglong
 *
 */
@RequestMapping("/filter")
@Controller
public class FilterController {
	@RequestMapping(value = "/aa/filter1")
	public String filtre1(Model model, HttpServletRequest req) {
		return "/index";
	}
}
