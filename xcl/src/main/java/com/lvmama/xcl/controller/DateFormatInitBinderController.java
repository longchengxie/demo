package com.lvmama.xcl.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 测试@InitBinder  @DateTimeFormat  日期格式化
 * @author xiechenglong
 *
 */
@Controller
@RequestMapping("/date")
public class DateFormatInitBinderController {

	/**
	 * Spring MVC前台时间字符串到后台Date的转换  @InitBinder
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping(value = "/format")
	public String dateFormatJS(Model model) {
		model.addAttribute("date", new Date());
		return "/date/dateFormat";
	}
	
	/**
	 * 以下两种字符串转日期方法二选一即可
	 */

	/**
	 * @DateTimeFormat字符串转日期
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/stringFormatDate")
	@ResponseBody
	public Object stringFormatDate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		System.out.println(date);
		return date;
	}

	/**
	 * @InitBinder字符串转日期
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/stringFormatDate2")
	@ResponseBody
	public Object stringFormatDate2(Date date) {
		System.out.println(date);
		return date;
	}
}
