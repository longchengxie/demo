package com.lvmama.xcl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xss")
public class XssController {

	@RequestMapping("/xss")
	public String xss(Model model,String name){
		model.addAttribute("name", name);
		return "/xss/xss";
	}
}
