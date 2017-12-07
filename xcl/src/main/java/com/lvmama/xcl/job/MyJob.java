package com.lvmama.xcl.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvmama.xcl.comm.jms.MessageFactory;
import com.lvmama.xcl.comm.jms.TopicMessageProducer;

public class MyJob implements Runnable{
	
	private static final Log LOGGER = LogFactory.getLog(MyJob.class);
	
	@Autowired
	private TopicMessageProducer xclMessageProducer;

	@Override
	public void run() {
		xclMessageProducer.sendMsg(MessageFactory.newXclMsgForBack(222222L,"job","job执行发送消息"));
		LOGGER.info("============执行我的job==============");
	}

}
