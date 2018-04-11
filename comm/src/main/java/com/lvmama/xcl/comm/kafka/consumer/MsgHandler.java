package com.lvmama.xcl.comm.kafka.consumer;

/**
 * 消息处理器
 */
public interface MsgHandler {

    /**
     * 执行方法
     * @param msg
     */
    void execu(Object msg);

}
