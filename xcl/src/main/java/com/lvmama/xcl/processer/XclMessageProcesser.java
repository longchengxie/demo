package com.lvmama.xcl.processer;

import com.lvmama.xcl.comm.jms.Message;
import com.lvmama.xcl.comm.jms.MessageProcesser;

public class XclMessageProcesser implements MessageProcesser{

	@Override
	public void process(Message message) {
		System.out.println("====activeMq========收到消息=========="+message.toString()+"=============");
		
	}

}
