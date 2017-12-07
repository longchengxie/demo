package com.lvmama.xcl.servlet;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6688700640807713294L;

	@Override
	@ResponseBody
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		// 正常标志
		String msg = null;
		String message = null;
		StringBuffer sb = request.getRequestURL();
		try {
			System.out.println("请求url=" + sb.toString());
			super.doDispatch(request, response);
		} catch (Exception e) {
			msg = e.getMessage();
			message = e.getMessage();
			e.printStackTrace();
		} finally {
			if (msg != null) {
				JSONObject obj = JSONObject.fromObject(message);
				pw = response.getWriter();
				if (obj != null) {
					pw.write(obj.toString());
				}
				if (pw != null) {
					pw.close();
				}
			}
		}
	}

}
