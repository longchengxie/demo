package com.lvmama.dest.common.httpsqs4j.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsMessage;
import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsMessageFactory;
import com.lvmama.dest.common.httpsqs4j.msg.HttpsqsProducer;

@Controller
@RequestMapping("/httpsqs")
public class HttpsqsController {
	
	@Autowired(required=false)
	HttpsqsProducer producer;
	
	@RequestMapping(value = "/test")
	public Object test() throws IOException {
		HttpsqsMessage message = HttpsqsMessageFactory.createExceptionLogMsg(JSON.toJSONString("agsagjsajsajs"));
		producer.sendMessage(message);
		return message;
	}
}
