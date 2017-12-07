package com.lvmama.dest.common.httpsqs4j.msg;

/** 
 * @Title: HttpsMessageProcesser.java 
 * @Package com.lvmama.com.lvtu.common.httpsqs4j.msg 
 * @Description: TODO 
 * @author dengcheng 
 * @date 2015-4-16 下午4:38:25 
 * @version V1.0.0 
 */
public interface HttpsqsMessageProcesser {
	void process(HttpsqsMessage message);
}
