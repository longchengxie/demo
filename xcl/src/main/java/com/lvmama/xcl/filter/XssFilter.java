package com.lvmama.xcl.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.xcl.comm.XssHttpServletRequestWrapper;


/**
 * 过滤掉HTML标签
 */
public class XssFilter implements Filter {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Default constructor.
	 */
	public XssFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        chain.doFilter(new XssHttpServletRequestWrapper(  
                (HttpServletRequest) request), response);  
    }  

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
