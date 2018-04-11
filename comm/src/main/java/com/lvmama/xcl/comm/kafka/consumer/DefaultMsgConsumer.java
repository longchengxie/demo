package com.lvmama.xcl.comm.kafka.consumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvmama.xcl.comm.utils.BeanHessionSerializeUtil;
import com.lvmama.xcl.comm.utils.ExceptionFormatUtil;

import java.util.List;

/**
 * 组模式消费者
 */
public class DefaultMsgConsumer implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(DefaultMsgConsumer.class);

    private KafkaStream<byte[],byte[]> kafkaStream;
    private MsgHandler msgHandler;

    public DefaultMsgConsumer(KafkaStream<byte[],byte[]> kafkaStream,MsgHandler msgHandler){
        if(kafkaStream == null || msgHandler == null){
            logger.error(" The kafkaStream or msghandlerList in Constructor's parameters can not be empty !!!");
            return;
        }
        this.kafkaStream = kafkaStream;
        this.msgHandler = msgHandler;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
        while (iterator.hasNext()){
            try {
                byte[] msgBytes = iterator.next().message();
                Object message = BeanHessionSerializeUtil.deserialize(msgBytes);
                msgHandler.execu(message);
            } catch (Exception e) {
                logger.error(ExceptionFormatUtil.getTrace(e));
            }
        }
    }

}
