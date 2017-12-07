package com.lvmama.xcl.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.lvmama.xcl.utils.XssHTMLUtil;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	public String getParameter(String parameter) {  
        String value = super.getParameter(parameter);  
        if (value == null) {  
               return null;  
                }  
        return cleanXSS(value);  
	}
	
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}
	
	private String cleanXSS(String value) {  
		XssHTMLUtil xssHTMLUtil = new XssHTMLUtil();
        return xssHTMLUtil.filter(value);  
    }  
	
}
