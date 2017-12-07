package com.lvmama.dest.common.httpsqs4j.msg;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lvmama.dest.common.httpsqs4j.Httpsqs4j;
import com.lvmama.dest.common.httpsqs4j.HttpsqsClient;
import com.lvmama.dest.common.httpsqs4j.HttpsqsException;
import com.lvmama.xcl.comm.Constant;

/**
* @author yusenhua
* 
*/
@Component("httpsqsLogProducer")
public class HttpsqsLogProducer implements  InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpsqsLogProducer.class);

	HttpsqsClient client  = null;
	
	@Async
	public void sendMessage(HttpsqsMessage message) throws IOException{
		LOGGER.info("start send isBuyLog");
		LOGGER.info("获取message"+message);
		final String jsonMessage = JSON.toJSONString(message);
		LOGGER.info("获取jsonMessage"+jsonMessage);
		
		
		String queueName=Constant.getInstance().getProperty("queue_name");
		LOGGER.info("获取queueName"+queueName);
		try {
			client.putString(queueName, jsonMessage);
		} catch (HttpsqsException e) {
			LOGGER.info("error send isBuyLog"+e);
		}			
		
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		String stringUri  = Constant.getInstance().getProperty("httpsqs_uri");		
		/*if(client==null){
			try {
				if(stringUri==null){
					throw new RuntimeException("Httpsqs uri not been null ");
				}
				URI uri = new URI(stringUri);
				
				Httpsqs4j.setConnectionInfo(uri.getHost(), uri.getPort(),uri.getPath(), "UTF-8");
				client = Httpsqs4j.createNewClient();
			} catch (Exception e) {
				LOGGER.error("无法连接Httpsqs:" + e.getMessage());
			}
		}*/

	}

}
