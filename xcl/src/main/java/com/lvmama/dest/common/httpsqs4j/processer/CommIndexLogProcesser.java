package com.lvmama.dest.common.httpsqs4j.processer;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsMessage;
import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsMessageProcesser;


/**
* @author dengcheng
* @version 2015年12月21日 下午2:40:06
* @email dengcheng@lvmama.com
*/
public class CommIndexLogProcesser implements HttpsqsMessageProcesser{
	
	private static final Log LOG = LogFactory.getLog(CommIndexLogProcesser.class);
	
	
	@Override
	public void process(HttpsqsMessage message) {
		System.out.println("...............................CommIndexLogProcesser................................");
		
	}

}
