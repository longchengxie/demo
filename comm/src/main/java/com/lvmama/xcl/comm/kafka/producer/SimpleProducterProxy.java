package com.lvmama.xcl.comm.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvmama.xcl.comm.utils.StringUtil;

/**
 * 简单的实现
 */
public class SimpleProducterProxy {

    private final Logger logger = LoggerFactory.getLogger(SimpleProducterProxy.class);

    protected String topic;
    protected MsgProducer msgProducer;

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void setMsgProducer(MsgProducer msgProducer) {
        this.msgProducer = msgProducer;
    }

    public boolean handOut(Object msg) {
        if(StringUtil.isEmptyString(this.topic)){
            logger.error(" The topic can not be empty ... ");
            return false;
        }
        return this.msgProducer.send(topic,msg);
    }

    public boolean handOut(Object key, Object msg) {
        if(StringUtil.isEmptyString(this.topic)){
            logger.error(" The topic can not be empty ... ");
            return false;
        }
        return this.msgProducer.send(topic,key,msg);
    }

    public boolean handOut(Object key, Object partKey, Object msg) {
        if(StringUtil.isEmptyString(this.topic)){
            logger.error(" The topic can not be empty ... ");
            return false;
        }
        return this.msgProducer.send(topic,key,msg);
    }

}
