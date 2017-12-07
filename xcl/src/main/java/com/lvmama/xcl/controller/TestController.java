package com.lvmama.xcl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lvmama.xcl.vo.Person;
import com.lvmama.xcl.vo.User;


@Controller
@RequestMapping("test")
public class TestController {
	@RequestMapping(value = "/test1")
	@ResponseBody
	public Object test1(@RequestParam(value = "id", required = false, defaultValue = "99") String id) {
		return id == null ? 0 : id;
	}

	@RequestMapping(value = "/{name}/test2/{id}")
	@ResponseBody
	public Object test2(@PathVariable(value = "name") String name, @PathVariable(value = "id") String id) {
		return id == null ? 0 : id + name;
	}

	@RequestMapping(value = "/test3")
	@ResponseBody
	public Object test3(
			@CookieValue(value = "lvmama_ab_testing_uuid", required = false, defaultValue = "sgcsgc") String jsessionid) {
		return jsessionid;
	}

	@RequestMapping(value = "/test4")
	public String test4(Model model) {
		return "/form";
	}

	/**
	 * 基本数据类型绑定,POJO对象绑定参数,集合List绑定：List的绑定需要将List对象包装到一个类中才能绑定
	 * 
	 * @param name
	 * @param age
	 * @param income
	 * @param isMarried
	 * @param interests
	 * @param user
	 * @param list2
	 * @param array
	 */
	@RequestMapping(value = "/test5")
	@ResponseStatus(value = HttpStatus.OK)
	public void test5(String name, Integer age, Double income, Boolean isMarried, String[] interests, User user,
			@RequestParam(value = "list2", required = false) List<String> list2,
			@RequestParam(value = "array", required = true, defaultValue = "3,4,5,6,7,8,9") String[] array) {
		System.out.println("=============================");
		System.out.println(name);
		System.out.println(age);
		System.out.println(income);
		System.out.println(isMarried);
		for (String s : interests) {
			System.out.println(s.toString());
		}

		for (String l : user.getList()) {
			System.out.println(l.toString());
		}

		for (String s : list2) {
			System.out.println(s.toString());
		}

		for (String s : array) {
			System.out.println(s.toString());
		}
		System.out.println("=============================");
	}

	/**
	 * 将提交的json数据转换为java对象
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping(value = "/test6")
	@ResponseBody
	public Object test4(@RequestBody Person person) {
		return person;
	}

	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "show")
	// 指定请求的url
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "test/show 测试");
		return mv;
	}

	@RequestMapping(value = "/*/show2")
	// 指定请求的url
	public ModelAndView show2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "Ant风格的URL映射 -- *");
		return mv;
	}

	@RequestMapping(value = "/**/show3")
	// 指定请求的url
	public ModelAndView show3() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "Ant风格的URL映射 -- **");
		return mv;
	}

	@RequestMapping(value = "/show4/{id}")
	// 指定请求的url
	public ModelAndView show4(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "占位符请求规则 id = " + id);
		return mv;
	}

	@RequestMapping(value = "/show5/{id}")
	// 指定请求的url
	public ModelAndView show5(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "小误区测试  占位符请求规则 id = " + id);
		return mv;
	}

	@RequestMapping(value = "/show6", method = RequestMethod.POST)
	// 指定请求的url
	public ModelAndView show6() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "看到这个页面就说明你是POST请求!");
		return mv;
	}

	@RequestMapping(value = "/show7", method = { RequestMethod.POST, RequestMethod.PUT })
	// 指定请求的url
	public ModelAndView show7() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "看到这个页面就说明你是POST或PUT请求!");
		return mv;
	}

	// url : /test/show8.do?id=123
	@RequestMapping(value = "/show8", params = "id")
	// 指定请求的url
	public ModelAndView show8(@RequestParam("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "限制请求参数 id = " + id);
		return mv;
	}

	@RequestMapping(value = "/show9")
	// 指定请求的url
	public ModelAndView show9(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("show");
		StringBuilder sb = new StringBuilder();
		sb.append("request = " + request + "<br>");
		sb.append("response = " + response + "<br>");
		sb.append("session = " + session + "<br>");
		mv.addObject("msg", sb.toString());

		// 测试request是否可用
		request.setAttribute("param1", "request的参数");

		return mv;
	}

	@RequestMapping(value = "/show10")
	public ModelAndView show10(@RequestParam(value = "id", required = true, defaultValue = "1") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "测试@RequestParam id = " + id);
		return mv;
	}

	@RequestMapping(value = "/show11")
	public ModelAndView show11(@CookieValue("JSESSIONID") String jsessionid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "JSESSIONID = " + jsessionid);
		return mv;
	}

	@RequestMapping(value = "/show12", method = RequestMethod.POST)
	public ModelAndView show12(User user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "user = " + user);
		return mv;
	}

	@RequestMapping("/show13")
	@ResponseStatus(value = HttpStatus.OK)
	// 无需跳转页面，直接返回200状态
	public void show13(String name, Integer age, Double income, Boolean isMarried, String[] interests) {
		System.out.println("简单数据类型绑定=========");
		System.out.println("名字:" + name);
		System.out.println("年龄:" + age);
		System.out.println("收入:" + income);
		System.out.println("已结婚:" + isMarried);
		System.out.println("兴趣:");
		for (String interest : interests) {
			System.out.println(interest);
		}
		System.out.println("====================");
	}

	/**
	 * 测试@RequestBody
	 * 
	 * @return
	 */
	@RequestMapping("/show17")
	public ModelAndView show17(@RequestBody User user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("msg", "user = " + user);
		return mv;
	}
	
	@RequestMapping(value="/show18")
	public String show18(){
		//return "redirect:/user/4.do";
		return "redirect:http://localhost:9090/test1/user/4.do";
	}
}
