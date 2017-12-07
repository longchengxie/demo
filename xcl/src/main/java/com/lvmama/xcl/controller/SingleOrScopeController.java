package com.lvmama.xcl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lvmama.xcl.service.IndexService;
/**
 * 单例多例       私有，静态，公用变量测试
 * @author xiechenglong
 *
 */
@Controller
//@Scope("prototype")
@RequestMapping("/")
public class SingleOrScopeController {
	
	private int index = 0;
	private static int staticIndex = 0;
	public String x = null;
	
	@Autowired
	private IndexService indexServiceImpl;
	
	@Autowired
	private IndexService indexServiceImpl2;

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest re) {
		HttpSession session = re.getSession();
		String id = session.getId();
		System.out.println(id);
		System.out.println(indexServiceImpl.hashCode());
		System.out.println("................................................");
		System.out.println(indexServiceImpl2.hashCode());
		System.out.println(indexServiceImpl2.getClass().hashCode());
		System.out.println("................................................");
		System.out.println(indexServiceImpl == indexServiceImpl2);
		return "/index";
	}
	
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String test(ModelMap model, String isOn) {
		System.out.println("------------------test------------------");
		
		if(StringUtils.isNotBlank(isOn)){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String t = Thread.currentThread().getName();
		
		t = "普通属性："+(index++)+",静态属性："+(staticIndex++)+",   hashcode="+this.hashCode();
		System.out.println(t);
		
		if (x == null){
			x = "11";
		}else{
			x = "22";
		}
		System.out.println(x);
		
		return "/index";
	}

}
