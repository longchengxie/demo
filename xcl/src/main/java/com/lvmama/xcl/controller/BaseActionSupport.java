package com.lvmama.xcl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lvmama.xcl.threadlocal.HttpServletLocalThread;

public class BaseActionSupport {
	protected Logger LOGGER = Logger.getLogger(this.getClass());

	public void sendAjaxMsg(String msg, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(msg);
			out.flush();
			out.close();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public void sendAjaxMsg(String msg) {
		this.sendAjaxMsg(msg, HttpServletLocalThread.getRequest(), getResponse());
	}

	/**
	 * 发送Ajax请求结果json
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendAjaxResultByJson(String json, HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public void sendAjaxResultByJson(String json) {
		this.sendAjaxResultByJson(json, getResponse());
	}

	protected HttpServletResponse getResponse() {
		return HttpServletLocalThread.getResponse();
	}
}
