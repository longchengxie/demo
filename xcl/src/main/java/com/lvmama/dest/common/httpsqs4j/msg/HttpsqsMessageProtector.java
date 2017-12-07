package com.lvmama.dest.common.httpsqs4j.msg;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** 
 * @Title: HttsqsMessageProtector.java 
 * @Package com.lvmama.com.lvtu.common.httpsqs4j.msg 
 * @Description: TODO 
 * @author dengcheng 
 * @date 2015-4-16 下午3:36:37 
 * @version V1.0.0 
 */
public class HttpsqsMessageProtector {
	private static final Log log = LogFactory.getLog(HttpsqsMessageProtector.class);
	private static HttpsqsMessageProtector instance;
	private Map<String, String[]> topicMap = new HashMap<String, String[]>();
	
	private void init() {
		String[] httpsqsMap = new String[] { "HttpSqs.Common.dest-bridge-schedule"};
		
		String[] httpsqsOrderMap = new String[] { "HttpSqs.Common.dest-hotel-dock"};
		
		String[] httpsqsTestMap = new String[] { "HttpSqs.test.test-test"};
		
		topicMap.put("HttpSqs.order", httpsqsOrderMap);
		
		topicMap.put("HttpSqs.log", httpsqsMap);
		topicMap.put("HttpSqs.email", httpsqsMap);
		topicMap.put("HttpSqs.test", httpsqsTestMap);
	}
	
	/**
	 * 获得重试队列
	* @Description: TODO 
	* @author dengcheng 
	* @date 2015-4-16 下午3:40:26
	 */
	@Deprecated
	public String getRetryQueue(String queueName){
		return topicMap.get(queueName)+"."+"RETRY.";
	}
	
	
	public static HttpsqsMessageProtector getInstance() {
		if (instance==null) {
			HttpsqsMessageProtector protector = new HttpsqsMessageProtector();
			protector.init();
			instance = protector;
		}
		return instance;
	}
	
	public String[] getQueues(String topic) {
		return topicMap.get(topic);
	}
	
}
