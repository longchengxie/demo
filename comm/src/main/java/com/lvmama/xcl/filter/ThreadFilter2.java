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

public class ThreadFilter2 implements javax.servlet.Filter {
	private static final Log LOG = LogFactory.getLog(ThreadFilter2.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		LOG.info("ThreadFilter2--------------------" + req.getRequestURL() + " ------------------ start");
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOG.info("ThreadFilter2--------------------" + req.getRequestURL() + " ------------------ end");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
