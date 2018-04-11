package com.lvmama.xcl.comm.kafka.consumer;


import java.util.List;

/**
 * 消费者组接口
 */
public interface MsgConsumerGroup {

    /**
     *  消费消息
     * @param msgHandler 消息处理器
     */
    void execute(MsgHandler msgHandler);

    /**
     * 关闭消费者
     */
    void shutdown();

}
