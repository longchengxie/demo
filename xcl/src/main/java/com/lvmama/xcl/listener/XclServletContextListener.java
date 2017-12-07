package com.lvmama.xcl.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;

/**
 * 每次启动vst_order的时候生成一个六位随机数的版本号，储存在application作用域中
 * 版本号作用于js、css请求的url，以防止js、css缓存。
 * 
 * @author xiechenglong
 *
 */
public class XclServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		WebApplicationContext context = (WebApplicationContext) sce.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhh");
		String currentDateString = simpleDateFormat.format(date);
		context.getServletContext().setAttribute("version", currentDateString);// 设置版本号到作用域中
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
