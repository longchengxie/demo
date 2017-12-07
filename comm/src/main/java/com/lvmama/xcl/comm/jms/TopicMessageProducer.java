package com.lvmama.xcl.comm.jms;

import java.net.InetAddress;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;


public class TopicMessageProducer {

	private static final Log log = LogFactory
			.getLog(TopicMessageProducer.class);

	private JmsTemplate template;

	private String destination;
	/**
	 * 向一个 Queue 组发送JMS，达到 Topic 效果
	 * @param msg
	 */
	public void sendMsg(Message msg) {
		log.info(msg);
		String[] queues = MessageProtector.getInstance().getQueues(destination);
		log.info("queue -------------"+StringUtils.join(queues,","));
		try {
			if (null != queues) {
				for (String string : queues) {
					log.info("queue:" + string + ", msg:" + msg);
					template.convertAndSend(string, msg);
				    log.info("queue:" + string + ", msg:" + msg);
				}
			}
		} catch(Throwable e) {
			log.error("-------Error-------", e);
		}
		

	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
