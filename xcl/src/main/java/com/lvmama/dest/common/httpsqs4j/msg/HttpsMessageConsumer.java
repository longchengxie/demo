package com.lvmama.dest.common.httpsqs4j.msg;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.lvmama.dest.common.httpsqs4j.Httpsqs4j;
import com.lvmama.dest.common.httpsqs4j.HttpsqsClient;
import com.lvmama.dest.common.httpsqs4j.HttpsqsException;

/**
 * @Title: HttpsMessageConsumer.java
 * @Package com.lvmama.com.lvtu.common.httpsqs4j.msg
 * @Description: TODO
 * @author dengcheng
 * @date 2015-4-16 下午4:39:37
 * @version V1.0.0
 */
public class HttpsMessageConsumer implements InitializingBean {
	List<HttpsqsMessageProcesser> processerList = new ArrayList<HttpsqsMessageProcesser>();
	HttpsqsClient client = null;
	private static final Log log = LogFactory.getLog(HttpsMessageConsumer.class);
	private String destName;
	private String stringUri;

	private void recieve() throws InterruptedException {
		/**
		 * 因为httpsqs 队列是拉的模式 所以这里阻塞，循环请求
		 */
		while (true) {
			Thread.sleep(50);
			/**
			 * 防止并发过多 这里每个线程每次获取队列数据侯阻塞20ms 这个值请不要修改，按需调整，或者多开消费者。
			 */
			/*try {
				// log.info("httpsqs get queuename :"+destName);
				String message = client.getString(destName);
				// log.info("httpsqs message:"+message);
				HttpsqsMessage httpMessage = JSON.parseObject(message, HttpsqsMessage.class);
				for (HttpsqsMessageProcesser processer : processerList) {
					processer.process(httpMessage);
				}
			} catch (Exception e) {
				if (e != null && e.getMessage() != null && !e.getMessage().contains("There's no data")) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}*/

		}
	}

	public List<HttpsqsMessageProcesser> getProcesserList() {
		return processerList;
	}

	public void setProcesserList(List<HttpsqsMessageProcesser> processerList) {
		this.processerList = processerList;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getStringUri() {
		return stringUri;
	}

	public void setStringUri(String stringUri) {
		this.stringUri = stringUri;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		try {
			log.info("#### connecting httpsqs");
			if (stringUri == null) {
				throw new RuntimeException("Httpsqs uri not been null ");
			}
			URI uri = new URI(stringUri);
			Httpsqs4j.setConnectionInfo(uri.getHost(), uri.getPort(), uri.getPath(), "UTF-8");
		} catch (HttpsqsException e) {
			log.error("无法连接Httpsqs:" + e.getMessage());
		}
		client = Httpsqs4j.createNewClient();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					recieve();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

}
