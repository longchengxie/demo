package com.lvmama.xcl.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.xcl.hold.pub.DistributedContext;
import com.lvmama.xcl.net.utils.NetUtils;



public class ThreadFilter1 implements javax.servlet.Filter {
	private static final Log LOG = LogFactory.getLog(ThreadFilter1.class);
	private String[] excludeUrls;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludeUrls = (".*/msg/initMessage.do,.*/msg/.*,.*/remoting/.*,.*\\.js,.*\\.css,.*\\.gif,.*\\.jpg,.*\\.jpeg,."
				+ "*\\.jpe,.*\\.jfif,.*\\.bmp,.*\\.tif,.*\\.png,.*\\.ico").split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI().toLowerCase();
		for (String str : excludeUrls) {
			if (uri.toLowerCase().matches(str.toLowerCase().trim())) {
				chain.doFilter(request, response);
				return;
			}
		}

		try {
			System.out.println("ThreadFilter1 ----------  18位随机数:"+getRandom18String());
			Object webIPObj = DistributedContext.getContext().get("webIP");
			Object broserIPObj = DistributedContext.getContext().get("broserIP");
			if (webIPObj == null || broserIPObj == null) {
				String broserIP = getRemoteIpAddress(req);
				String webIP = NetUtils.getLocalIP();
				LOG.info("ThreadFilter1--------------------Broser IP=" + broserIP + ", Web ip=" + webIP);
				DistributedContext.put("broserIP", broserIP);
				DistributedContext.put("webIP", webIP);
			} else {
				LOG.info("ThreadFilter1--------------------Broser IP=" + broserIPObj + ", Web ip=" + webIPObj);
			}
			LOG.info("ThreadFilter1--------------------" + req.getRequestURL() + " ------------------ start");
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOG.info("ThreadFilter1--------------------" + req.getRequestURL() + " ------------------ end");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();

		}
		
		ip = transferIP(ip);
		
		return ip;
	}
	
	private static String transferIP(String ip) {
		if (ip != null && (ip.equals("127.0.0.1") || ip.startsWith("0:0:0:0") || ip.equals("localhost"))) {
			ip = NetUtils.getLocalIP();
		}

		return ip;

	}
	
	public static String getRandom18String() {
		Long millis = Long.valueOf(System.currentTimeMillis());
		Integer n = Integer.valueOf((int) (Math.random() * 100000.0D));
		int zeroNumber = 5 - n.toString().length();
		String pre = "";
		for (int i = 0; i < zeroNumber; ++i) {
			pre = pre + "0";
		}
		String r = millis.toString();
		if (!(pre.equals("")))
			r = r + pre + n.toString();
		else {
			r = r + n.toString();
		}

		return Long.toHexString(Long.parseLong(r));
	}
}
