package com.lvmama.xcl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lvmama.xcl.comm.kafka.consumer.MsgHandler;
import com.lvmama.xcl.prod.query.po.ProdQueryDoc;

/**
 * 产品公共查询，接收子系统消息的处理器
 */
@Service("prodQueryDocHandler")
public class ProdQueryDocHandlerImpl implements MsgHandler {

    private final Logger logger = LoggerFactory.getLogger(ProdQueryDocHandlerImpl.class);


    @Override
    public void execu(Object msg) {
        if(msg == null){
            logger.error("#ProdQueryDocProcessor# ===>> msg is null");
            return;
        }
        ProdQueryDoc prodQueryDoc = (ProdQueryDoc) msg;
        System.out.println(prodQueryDoc.toString());
    }


}
