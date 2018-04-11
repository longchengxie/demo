package com.lvmama.xcl.prod.query.po;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lvmama.xcl.comm.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品公共查询，对外暴露的接口的对象
 *
 * 如果只修改了产品，需要将 prodProduct prodDest prodBrand prodDistrict 传过来
 * 如果只修改了商品，需要将 suppGoods 传过来
 *
 * 具体的Json格式如下
 *
 * {
 *     "prodProduct": {
 *          "productId": 123,
 *          "categoryId": 123,
 *          "productName": "邮轮产品",
 *          "cancelFlag": "Y",
 *          "recommendLevel": 123,
 *          "saleFlag": "Y",
 *          "districtId": 123,
 *          "managerId": 123,
 *          "filiale": "子公司",
 *          "urlId": "123",
 *          "createUser": "123",
 *          "createTime": "yyyy-MM-dd HH:mm:ss SSS",
 *          "updateUser": "123",
 *          "updateTime": "yyyy-MM-dd HH:mm:ss SSS",
 *          "productType": "123",
 *          "packageType": "123",
 *          "auditStatus": "123",
 *          "sensitiveFlag": "123",
 *          "suppProductName": "123",
 *          "source": "123",
 *          "bu": "123",
 *          "attributionId": 123,
 *          "companyType": "123",
 *          "muiltDepatrueFlag": "123",
 *          "abandonFlag": "Y",
 *          "managerIdPerm": "123",
 *          "modelVersion": 123,
 *          "subCategoryId": 123,
 *          "travellerDelayFlag": "Y",
 *          "syncDetailFlag": "123",
 *          "ebkSupplierGroupId": 123
 *     },
 *     "suppGoods": [
 *          {
 *              "productId": 123,
 *              "categoryId": 123,
 *              "suppGoodsId": 123,
 *              "managerId": 123,
 *              "filiale": "上海分公司",
 *              "supplierId": 123
 *          }
 *     ],
 *     "prodDest": [
 *          {
 *              "destId": 123
 *          }
 *     ],
 *     "prodBrand": {
 *          "prodBrandId": 123
 *     },
 *     "prodDistrict": {
 *          "districtId": 123,
 *          "districtName": "上海"
 *     }
 * }
 *
 */
public class ProdQueryDoc implements Serializable {

    private static final long serialVersionUID = 801357136605332164L;

    private Long productId;
    private Long categoryId;
    private String prodDocu;
    private String originType;
    private Date createTime;
    private String caseType;
    private Long caseId;

    /**
     * 消息来源
     */
    public enum ORIGIN_TYPE {
        VISA("签证子系统"),SHIP("邮轮子系统"),LINE("线路子系统"),LOCAL_PALY("当地玩乐");
        private String cName;

        ORIGIN_TYPE(String cName) {
            this.cName = cName;
        }
    }

    /**
     * 业务场景
     */
    public enum CASE_TYPE {
        ALL("所有场景"), PROD_PRODUCT("产品修改"), SUPP_GOODS("商品修改");
        private String cName;
        CASE_TYPE(String cName) {
            this.cName = cName;
        }
        public String getcName() {
            return cName;
        }
    }

    /**
     * 是否有错误
     */
    public boolean hasError(){
        if(productId != null && categoryId != null && StringUtils.isNotEmpty(prodDocu)
                && StringUtils.isNotEmpty(originType) && StringUtils.isNotEmpty(caseType)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(", productId: " + productId);
        stringBuffer.append(", categoryId: " + categoryId);
        stringBuffer.append(", prodDocu: " + prodDocu);
        stringBuffer.append(", originType: " + originType);
        stringBuffer.append(", createTime: " + (createTime == null ? null : DateUtil.formatDate(createTime,"yyyy-MM-dd HH:mm:ss SSS")));
        stringBuffer.append(", caseType: " + caseType);
        stringBuffer.append(", caseId: " + caseId);
        return stringBuffer.toString();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProdDocu() {
        return prodDocu;
    }

    public void setProdDocu(String prodDocu) {
        this.prodDocu = prodDocu;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
