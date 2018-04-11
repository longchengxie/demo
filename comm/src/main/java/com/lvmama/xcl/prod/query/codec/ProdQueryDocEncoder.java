package com.lvmama.xcl.prod.query.codec;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvmama.xcl.comm.utils.BeanHessionSerializeUtil;
import com.lvmama.xcl.prod.query.po.ProdQueryDoc;

/**
 * 产品查询对象编码器
 */
public class ProdQueryDocEncoder implements Encoder<ProdQueryDoc> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProdQueryDocEncoder.class);

    public ProdQueryDocEncoder(VerifiableProperties verifiableProperties){}

    @Override
    public byte[] toBytes(ProdQueryDoc prodQueryDoc) {
        if(prodQueryDoc == null){
            LOGGER.error("prodQueryDoc is null");
            return null;
        }
        return BeanHessionSerializeUtil.serialize(prodQueryDoc);
    }

}
