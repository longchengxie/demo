package com.lvmama.dest.common.httpsqs4j.processer;


import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsMessage;
import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsMessageProcesser;

/**
* @author dengcheng
* @version 2015年12月21日 下午2:40:06
* @email dengcheng@lvmama.com
*/
public class CommMySQLLogProcesser implements HttpsqsMessageProcesser{
	
	
	@Override
	public void process(HttpsqsMessage message) {
		System.out.println("......................CommMySQLLogProcesser..............................");
	}

}
