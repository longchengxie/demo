package com.lvmama.xcl.job;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lvmama.xcl.comm.jms.MessageFactory;
import com.lvmama.xcl.comm.jms.TopicMessageProducer;
import com.lvmama.xcl.comm.kafka.producer.SimpleProducterProxy;
import com.lvmama.xcl.prod.query.po.ProdQueryDoc;

public class MyJob implements Runnable{
	
	private static final Log LOGGER = LogFactory.getLog(MyJob.class);
	
	@Autowired
	private TopicMessageProducer xclMessageProducer;//activeMq
	
	/*@Resource(name="prodQueryHotelProducerProxy")
	private SimpleProducterProxy prodQueryHotelProducerProxy;*///kafka

	@Override
	public void run() {
		xclMessageProducer.sendMsg(MessageFactory.newXclMsgForBack(222222L,"job","job执行发送消息"));
		System.out.println("============job发送消息================");
		
		//prodQueryHotelProducerProxy.handOut(String.valueOf("110"),new ProdQueryDoc());
		
		LOGGER.info("============执行我的job==============");
	}

}
