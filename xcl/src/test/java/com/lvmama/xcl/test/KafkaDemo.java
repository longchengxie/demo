package com.lvmama.xcl.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.lvmama.xcl.comm.kafka.producer.DefaultMsgProducer;
import com.lvmama.xcl.prod.query.po.ProdQueryDoc;

/**
 * Created by Administrator on 2016/12/27.
 */
public class KafkaDemo {

    public static void main(String[] args) {
        DefaultMsgProducer defaultMsgProducer = new DefaultMsgProducer();
        defaultMsgProducer.setBootstrapServers("10.112.4.95:9091,10.112.4.95:9092,10.112.4.95:9093");
        defaultMsgProducer.setSendEnable(true);
        Properties properties = new Properties();
        properties.put("serializer.class", "com.lvmama.xcl.prod.query.codec.ProdQueryDocEncoder");
        properties.put("key.serializer.class", "kafka.serializer.StringEncoder");
        defaultMsgProducer.setProperties(properties);
        defaultMsgProducer.init();

        long start = System.currentTimeMillis();
        for(ProdQueryDoc prodQueryDoc : get()){
            for(int i=0;i<1;i++){
                boolean status = defaultMsgProducer.send("PROD_QUERY", String.valueOf(prodQueryDoc.getProductId()), prodQueryDoc);
                if(!status) System.out.println("error ... ");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("It costs "+(end-start)+" millis.");
    }


    private static List<ProdQueryDoc> get(){
        List<ProdQueryDoc> prodList = new ArrayList<>();

        ProdQueryDoc prodQueryDoc1 = new ProdQueryDoc();
        prodQueryDoc1.setProductId(264926l);
        prodQueryDoc1.setOriginType("SHIP");
        String prodDocu1 = "{\"prodDistrict\":{},\"prodProduct\":{\"abandonFlag\":\"N\",\"auditStatus\":\"AUDITTYPE_PASS\",\"bu\":\"OUTBOUND_BU\",\"cancelFlag\":\"Y\",\"categoryId\":2,\"createTime\":\"2014-11-05 00:00:00 000\",\"createUser\":\"lv6255\",\"ebkSupplierGroupId\":0,\"managerId\":6022,\"managerIdPerm\":\",6022,6179,\",\"muiltDpartureFlag\":\"N\",\"productId\":264926,\"productName\":\"钻石公主号\",\"recommendLevel\":2,\"saleFlag\":\"Y\",\"senisitiveFlag\":\"Y\",\"source\":\"BACK\",\"travellerDelayFlag\":\"Y\",\"updateTime\":\"2017-01-07 01:06:12 000\",\"urlId\":\"diamondprincess\"},\"prodDest\":[],\"prodBrand\":{},\"suppGoods\":[{\"categoryId\":2,\"filiale\":\"GZ_FILIALE\",\"managerId\":6022,\"productId\":264926,\"suppGoodsId\":3314667}]}";
        prodQueryDoc1.setProdDocu(prodDocu1);
        prodQueryDoc1.setCategoryId(2l);
        prodQueryDoc1.setCaseType("PROD_PRODUCT");
        prodQueryDoc1.setCreateTime(new Date());
        prodList.add(prodQueryDoc1);

//        ProdQueryDoc prodQueryDoc2 = new ProdQueryDoc();
//        prodQueryDoc2.setProductId(450046l);
//        prodQueryDoc2.setOriginType("SHIP");
//        String prodDocu2 = "{\"prodDistrict\":{\"districtId\":72418,\"districtName\":\"延安测试\"},\"prodProduct\":{\"abandonFlag\":\"N\",\"auditStatus\":\"AUDITTYPE_TO_PM\",\"bu\":\"OUTBOUND_BU\",\"cancelFlag\":\"N\",\"categoryId\":8,\"createTime\":\"2017-01-16 08:25:43 000\",\"createUser\":\"admin\",\"ebkSupplierGroupId\":0,\"filiale\":\"SH_FILIALE\",\"managerId\":2781,\"muiltDpartureFlag\":\"N\",\"packageType\":\"SUPPLIER\",\"productId\":450046,\"productName\":\"1221邮轮组合产品\",\"productType\":\"FOREIGNLINE\",\"recommendLevel\":2,\"saleFlag\":\"N\",\"senisitiveFlag\":\"Y\",\"source\":\"BACK\",\"suppProductName\":\"1\",\"travellerDelayFlag\":\"N\",\"updateTime\":\"2017-01-16 08:25:43 000\",\"urlId\":\"450046\"},\"prodDest\":[],\"prodBrand\":{},\"suppGoods\":[]}";
//        prodQueryDoc2.setProdDocu(prodDocu2);
//        prodQueryDoc2.setCategoryId(8l);
//        prodQueryDoc2.setCaseType("PROD_PRODUCT");
//        prodQueryDoc2.setCreateTime(new Date());
//        prodList.add(prodQueryDoc2);

//        ProdQueryDoc prodQueryDoc3 = new ProdQueryDoc();
//        prodQueryDoc3.setProductId(650220l);
//        prodQueryDoc3.setOriginType("SHIP");
//        String prodDocu3 = "{\"prodDistrict\":{},\"prodProduct\":{\"abandonFlag\":\"N\",\"auditStatus\":\"AUDITTYPE_PASS\",\"bu\":\"OUTBOUND_BU\",\"cancelFlag\":\"Y\",\"categoryId\":2,\"createTime\":\"2016-02-03 00:00:00 000\",\"createUser\":\"lv6255\",\"ebkSupplierGroupId\":0,\"managerIdPerm\":\",3717,2883,\",\"muiltDpartureFlag\":\"N\",\"productId\":650220,\"productName\":\"MSC地中海邮轮-抒情号\",\"recommendLevel\":2,\"saleFlag\":\"Y\",\"senisitiveFlag\":\"N\",\"source\":\"BACK\",\"travellerDelayFlag\":\"Y\",\"updateTime\":\"2017-01-07 01:11:16 000\",\"urlId\":\"lirica\"},\"prodDest\":[],\"prodBrand\":{},\"suppGoods\":[{\"categoryId\":2,\"filiale\":\"BJ_FILIALE\",\"managerId\":3717,\"productId\":650220,\"suppGoodsId\":3794995}]}";
//        prodQueryDoc3.setProdDocu(prodDocu3);
//        prodQueryDoc3.setCategoryId(2l);
//        prodQueryDoc3.setCaseType("PROD_PRODUCT");
//        prodQueryDoc3.setCreateTime(new Date());
//        prodList.add(prodQueryDoc3);

//        ProdQueryDoc prodQueryDoc4 = new ProdQueryDoc();
//        prodQueryDoc4.setProductId(106785l);
//        prodQueryDoc4.setOriginType("SHIP");
//        String prodDocu4 = "{\"prodDistrict\":{},\"prodProduct\":{\"abandonFlag\":\"N\",\"auditStatus\":\"AUDITTYPE_PASS\",\"bu\":\"OUTBOUND_BU\",\"cancelFlag\":\"Y\",\"categoryId\":2,\"ebkSupplierGroupId\":0,\"managerId\":4531,\"managerIdPerm\":\",4531,6563,4923,2883,5052,\",\"muiltDpartureFlag\":\"N\",\"productId\":106785,\"productName\":\"公主邮轮-蓝宝石公主号\",\"saleFlag\":\"Y\",\"senisitiveFlag\":\"N\",\"source\":\"BACK\",\"travellerDelayFlag\":\"Y\",\"updateTime\":\"2017-01-07 21:01:19 000\",\"urlId\":\"sapphire\"},\"prodDest\":[],\"prodBrand\":{},\"suppGoods\":[{\"categoryId\":2,\"filiale\":\"SH_FILIALE\",\"managerId\":2883,\"productId\":106785,\"suppGoodsId\":3615493}]}";
//        prodQueryDoc4.setProdDocu(prodDocu4);
//        prodQueryDoc4.setCategoryId(4l);
//        prodQueryDoc4.setCaseType("PROD_PRODUCT");
//        prodQueryDoc4.setCreateTime(new Date());
//        prodList.add(prodQueryDoc4);

        return prodList;
    }

}
