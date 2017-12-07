package com.lvmama.dest.common.httpsqs4j.msg;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSON;
import com.lvmama.dest.common.httpsqs4j.Httpsqs4j;
import com.lvmama.dest.common.httpsqs4j.HttpsqsClient;
import com.lvmama.dest.common.httpsqs4j.HttpsqsException;

/**
 * @Title: HttpsqsProducer.java
 * @Package com.lvmama.com.lvtu.common.httpsqs4j.msg
 * @Description: TODO
 * @author dengcheng
 * @date 2015-4-16 上午11:57:37
 * @version V1.0.0
 */
public class HttpsqsProducer implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsqsProducer.class);

	private String destination;
	HttpsqsClient client = null;
	private String stringUri;

	@Async
	public void sendMessage(HttpsqsMessage message) throws IOException {
		final String jsonMessage = JSON.toJSONString(message);
		String[] queues = HttpsqsMessageProtector.getInstance().getQueues(destination);
		for (final String queue : queues) {
			try {
				client.putString(queue, jsonMessage);
			} catch (HttpsqsException e) {
				e.printStackTrace();
			}
		}

	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		// 设置异线程池
		// this.stringUri = Constant.getValue("httpsqs_uri",
		// Constant.PROPERTY_FILE.HTTPSQS);
		// this.destination = Constant.getValue("httpsqs_node",
		// Constant.PROPERTY_FILE.HTTPSQS);
		//
		if (client == null) {
			try {
				if (stringUri == null) {
					throw new RuntimeException("Httpsqs uri not been null ");
				}
				URI uri = new URI(stringUri);

				Httpsqs4j.setConnectionInfo(uri.getHost(), uri.getPort(), uri.getPath(), "UTF-8");
				client = Httpsqs4j.createNewClient();
			} catch (Exception e) {
				LOGGER.error("无法连接Httpsqs:" + e.getMessage());
			}
		}
	}

	public String getStringUri() {
		return stringUri;
	}

	public void setStringUri(String stringUri) {
		this.stringUri = stringUri;
	}

}
