package com.lvmama.xcl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lvmama.xcl.comm.jms.MessageFactory;
import com.lvmama.xcl.comm.jms.TopicMessageProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-xcl-beans.xml"})
public class ActivemqTest {
	
	@Autowired
	private TopicMessageProducer xclMessageProducer;
	
	@Test
	public void test(){
		xclMessageProducer.sendMsg(MessageFactory.newXclMsgForBack(111L,"fuck","fuck"));
	}
}
