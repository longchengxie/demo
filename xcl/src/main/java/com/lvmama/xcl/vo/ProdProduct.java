package com.lvmama.xcl.vo;


import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class ProdProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final double MODEL_VERSION_1D0 = 1.0;

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}


	private String auditStatus;
	private Long categoryId;
	private Long productId;
	private Long attributionId;
	private String attributionName;
	private String producTourtType;
	private String prefixName;
	private String productName;
	
	private String cancelFlag;

	private String saleFlag;

	private Long recommendLevel;

	private Long bizCategoryId;
	
	private String bizCategoryName;
	
    private Long subCategoryId;// 子品类ID

	private Long bizDistrictId;

	private String imageUrl;

	private String senisitiveFlag;


	// 产品经理ID
	private Long managerId;

	private String urlId;

	private String filiale;

	private String bu;
	// 数据源ebk或者vst
	private String source;

	// vo 使用
	private String managerName;

	// 出发地(行政区域)
	private String districtName;

	// 打包类型
	private String packageType;

	// 是否仅组合销售
	private String packageFlag;

	// 线路类别
	private String productType;
	// 线路类别(vo使用)
	private String productTypeName;

	private String toTraffic;
	
	private String backTraffic;

	/**
	 * 存储预览的url路径
	 */
	private String url;

	/**
	 * 多出发地标志，'N'非多出发地，'Y'多出发地
	 */
	private String muiltDpartureFlag;

	/**
	 * 版本标记，存储1.0、2.0 ......
	 */
	private Double modelVersion;


	/**
	 * 产品权限
	 */
	private String managerIdPerm;

	

    /**
     * 详情同步标识，'N'未同步过，'Y'已经同步过
     */
    private String syncDetailFlag; 
    
 // 供应商产品名称
 	private String suppProductName;
	
	public String getSuppProductName() {
		return suppProductName;
	}

	public void setSuppProductName(String suppProductName) {
		this.suppProductName = suppProductName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 产录入源
	 * 
	 * 
	 * 
	 */
	public static enum PRODUCTSOURCE {

		EBK, BACK;
	}



	/**
	 * 参数类型（成人数、儿童数或者套餐份数） =Y 成人数、儿童数 默认值 =N 套餐份数
	 */

	private String hotelCombFlag = "Y";


	private Long dailyLowestPrice;

	// 后台下单和前台详情页面使用
	// 商品上成人数
	private Long baseAdultQuantity = 0L;
	// 商品上儿童数
	private Long baseChildQuantity = 0L;
	/**
	 * 出发地
	 */
	private String district;
	/**
	 * 目的地
	 */
	private String[] dest;


	/**
	 * 二维码图片
	 */
	private String urlQR;
	/**
	 * 是否开心驴行 Y/N
	 */
	private String isKaixinlvxing;

	/**
	 * 公司主体
	 */
	private String companyType;
	
	/**
	 * 最少起订份数
	 */
	private Long minOrderQuantity;

	/**
	 *供应商打包是否多出发地(总产子销)
	 */
	private String isMuiltDeparture;
	public Long getMinOrderQuantity() {
		return minOrderQuantity;
	}
	public void setMinOrderQuantity(Long minOrderQuantity) {
		this.minOrderQuantity = minOrderQuantity;
	}


	/**
	 * 线路产品退改说明
	 */
	private String routeProductChangeInstruction;

	public String getRouteProductChangeInstruction() {
		return routeProductChangeInstruction;
	}

	public void setRouteProductChangeInstruction(String routeProductChangeInstruction) {
		this.routeProductChangeInstruction = routeProductChangeInstruction;
	}

	/**
	 * 针对分销平台产品的退改说明
	 */
	private String refundInstructionForApi;

	public String getRefundInstructionForApi() {
		return refundInstructionForApi;
	}

	public void setRefundInstructionForApi(String refundInstructionForApi) {
		this.refundInstructionForApi = refundInstructionForApi;
	}

	//分销独立短信通道--是否只认驴妈妈标识
	private String onlyLvmamaFlag;
	
	//是否游玩人后置标示(Y/N)
	private String travellerDelayFlag;
	
	//EBK用户所属供应商组ID
	private Long ebkSupplierGroupId;
	
	public String getTravellerDelayFlag() {
		return travellerDelayFlag;
	}

	public void setTravellerDelayFlag(String travellerDelayFlag) {
		this.travellerDelayFlag = travellerDelayFlag;
	}

	public String getOnlyLvmamaFlag() {
		return onlyLvmamaFlag;
	}

	public void setOnlyLvmamaFlag(String onlyLvmamaFlag) {
		this.onlyLvmamaFlag = onlyLvmamaFlag;
	}
    

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String[] getDest() {
		return dest;
	}

	public void setDest(String[] dest) {
		this.dest = dest;
	}


	/*
	 * public List<ProdVisadocRe> getProdVisaDocRes() { return prodVisaDocRes; }
	 * 
	 * public void setProdVisaDocRes(List<ProdVisadocRe> prodVisaDocRes) {
	 * this.prodVisaDocRes = prodVisaDocRes; }
	 */

	public String getSenisitiveFlag() {
		return senisitiveFlag;
	}

	public void setSenisitiveFlag(String senisitiveFlag) {
		this.senisitiveFlag = senisitiveFlag;
	}



	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 产品所有的属性,值 如果是文本内容，则：key=编码CODE,value=属性内容 <br>
	 * 如果是字典，则：key=编码CODE,value=
	 * <code>List<com.lvmama.vst.back.prod.po.PropValue></code> <br>
	 * PropValue中，id=字典值,name=字典名,addValue=附加说明
	 */
	private Map<String, Object> propValue;


	// 产品缓存时间
	public static int PRODUCT_MEMCACHED_TIME_OUT = 8 * 60 * 60;


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag == null ? null : cancelFlag.trim();
	}

	public Long getRecommendLevel() {
		return recommendLevel;
	}

	public void setRecommendLevel(Long recommendLevel) {
		this.recommendLevel = recommendLevel;
	}

	public String getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
	}


	public String getPackageFlag() {
		return packageFlag;
	}

	public void setPackageFlag(String packageFlag) {
		this.packageFlag = packageFlag;
	}


	public Long getBizCategoryId() {
		return bizCategoryId;
	}

	public void setBizCategoryId(Long bizCategoryId) {
		this.bizCategoryId = bizCategoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getBizDistrictId() {
		return bizDistrictId;
	}

	public void setBizDistrictId(Long bizDistrictId) {
		this.bizDistrictId = bizDistrictId;
	}

	/**
	 * @return the propValue
	 */
	public Map<String, Object> getPropValue() {
		return propValue;
	}

	/**
	 * @param propValue
	 *            the propValue to set
	 */
	public void setPropValue(Map<String, Object> propValue) {
		this.propValue = propValue;
	}


	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 */
	public String getUrlId() {
		return urlId;
	}

	/**
	 */
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getFiliale() {
		return filiale;
	}

	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public String toString() {
		return "ProdProduct [productId=" + productId + ", productName="
				+ productName + ", cancelFlag=" + cancelFlag + ", saleFlag="
				+ saleFlag + ", recommendLevel=" + recommendLevel
				+ ", bizCategoryId=" + bizCategoryId + ", bizDistrictId="
				+ bizDistrictId + ", imageUrl=" + imageUrl + ", managerId="
				+ managerId + ", filiale=" + filiale + ", managerName="
				+ managerName + "]";
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	
	public static enum OPERATIONCATEGORY {

		LONGGROUP("长线跟团游"), SHORTGROUP("短线跟团游");
		private String cnName;

		public static String getCnName(String code) {
			for (OPERATIONCATEGORY item : OPERATIONCATEGORY.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		OPERATIONCATEGORY(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
	

	// 产品等级
		public static enum PRODUCTGRADE {

			LVHSH("驴惠实惠"), LVYZD("驴悠中端"), LVZGD("驴尊高端");

			private String cnName;

			public static String getCnName(String code) {
				for (PRODUCTGRADE item : PRODUCTGRADE.values()) {
					if (item.getCode().equals(code)) {
						return item.getCnName();
					}
				}
				return code;
			}

			PRODUCTGRADE(String name) {
				this.cnName = name;
			}

			public String getCode() {
				return this.name();
			}

			public String getCnName() {
				return this.cnName;
			}

			@Override
			public String toString() {
				return this.name();
			}
		}
	
	// 打包类型
	public static enum PACKAGETYPE {

		LVMAMA("自主打包"), SUPPLIER("供应商打包");

		private String cnName;

		public static String getCnName(String code) {
			for (PACKAGETYPE item : PACKAGETYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		PACKAGETYPE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}

	/**
	 * 是否是需要审核的品类
	 * 
	 * 2014年8月28日
	 * 
	 * @return
	 */
	public boolean isHasAuditCategory() {
		if (this.bizCategoryId == null) {
			return false;
		}
		//酒店
		if(this.bizCategoryId.equals(1L)){
			return true;
		}
		// 15跟团游 16 当地游 18自由行42定制游
		if (this.bizCategoryId.equals(15L) || this.bizCategoryId.equals(16L)
				|| this.bizCategoryId.equals(17L)
				|| this.bizCategoryId.equals(18L) || this.bizCategoryId.equals(42L) || this.bizCategoryId.equals(32L)) {
			return true;
		}
		// 11景区门票 12其它票 13组合套餐票31演出票
		if (this.bizCategoryId.equals(11L) || this.bizCategoryId.equals(12L)
				|| this.bizCategoryId.equals(13L)||bizCategoryId.equals(31L)) {
			return true;
		}

		// 28WIFI产品
		if (this.bizCategoryId.equals(28L)) {
			return true;
		}
		//41交通接驳
		if(this.bizCategoryId.equals(41L)){
			return true;
		}
		//43美食 44娱乐 45购物
		if(this.bizCategoryId.equals(43L) || this.bizCategoryId.equals(44L) || this.bizCategoryId.equals(45L)){
			return true;
		}
		
		return false;
	}

	// 打包类型
	public static enum AUDITTYPE {

		AUDITTYPE_NO_SUBMIT("待提交"), AUDITTYPE_TO_PM("待产品经理审核"), AUDITTYPE_BACK_PM(
				"产品经理退回,待提交"), AUDITTYPE_TO_QA("待QA审核"), AUDITTYPE_BACK_QA(
				"QA退回，待提交"), AUDITTYPE_TO_BUSINESS("待商务审核"), AUDITTYPE_BACK_BUSINESS(
				"商务退回，待提交"), AUDITTYPE_PASS("审核通过");

		private String cnName;

		public static String getCnName(String code) {
			for (AUDITTYPE item : AUDITTYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		/**
		 * 获得下一个审核状态
		 * 
		 * @param auditType
		 * @return
		 */
		public static AUDITTYPE getNextAuditType(AUDITTYPE auditType,
				String isPass, String isCancel, String source) {
			if (auditType == null)
				return null;

			AUDITTYPE nextAuditType = null;
			// 撤销审核
			if (StringUtils.isNotBlank(isPass) && isPass.equals("N")
					&& isCancel.equals("Y")) {
				// 撤销回退节点
				switch (auditType) {
				case AUDITTYPE_TO_QA: {
					nextAuditType = AUDITTYPE_TO_PM;
				}
					;
					break;
				case AUDITTYPE_TO_BUSINESS: {
					nextAuditType = AUDITTYPE_TO_PM;
				}
					;
					break;
				}
				if (auditType.equals(AUDITTYPE_TO_PM)
						|| auditType.equals(AUDITTYPE_TO_QA)
						|| auditType.equals(AUDITTYPE_TO_BUSINESS)) {

					if (auditType.equals(AUDITTYPE_TO_PM)) {

						if ("BACK".equalsIgnoreCase(source)) {
							return ProdProduct.AUDITTYPE.AUDITTYPE_TO_PM;
						} else {
							return ProdProduct.AUDITTYPE.AUDITTYPE_NO_SUBMIT;
						}
					} else {

						// QA和商务审核撤销时统一到产品经理节点
						return nextAuditType;
					}
				}
			}

			// QA审核不通过
			if (auditType.equals(AUDITTYPE.AUDITTYPE_TO_QA)
					&& StringUtils.isNotBlank(isPass) && isPass.equals("N")) {
				return AUDITTYPE.AUDITTYPE_BACK_QA;
			}

			// 商务审核不通过
			if (auditType.equals(AUDITTYPE.AUDITTYPE_TO_BUSINESS)
					&& StringUtils.isNotBlank(isPass) && isPass.equals("N")) {
				return AUDITTYPE.AUDITTYPE_BACK_BUSINESS;
			}
			// 产品经理审核不通过，退回
			if (auditType.equals(AUDITTYPE.AUDITTYPE_TO_PM)
					&& StringUtils.isNotBlank(isPass) && isPass.equals("N")) {

				return AUDITTYPE.AUDITTYPE_BACK_PM;
			}

			// 审核通过
			switch (auditType) {
			// 待提交
			case AUDITTYPE_NO_SUBMIT: {

				nextAuditType = AUDITTYPE.AUDITTYPE_TO_PM;
			}
				;
				break;
			case AUDITTYPE_TO_PM: {

				nextAuditType = AUDITTYPE.AUDITTYPE_TO_QA;
			}
				;
				break;
			// 产品经理打回，待提交
			case AUDITTYPE_BACK_PM: {
				nextAuditType = AUDITTYPE.AUDITTYPE_TO_PM;
			}
				;
				break;

			// 待QA审核
			case AUDITTYPE_TO_QA: {
				nextAuditType = AUDITTYPE.AUDITTYPE_TO_BUSINESS;
			}
				;
				break;
			// QA打回，待提交
			case AUDITTYPE_BACK_QA: {
				nextAuditType = AUDITTYPE.AUDITTYPE_TO_QA;
			}
				;
				break;
			// 待商务审核
			case AUDITTYPE_TO_BUSINESS: {
				nextAuditType = AUDITTYPE.AUDITTYPE_PASS;
			}
				;
				break;
			// 商务打回，待提交
			case AUDITTYPE_BACK_BUSINESS: {
				nextAuditType = AUDITTYPE.AUDITTYPE_TO_BUSINESS;
			}
				;
				break;
			// 审核通过
			case AUDITTYPE_PASS: {
				nextAuditType = AUDITTYPE.AUDITTYPE_PASS;
			}
				;
				break;
			}
			return nextAuditType;
		}

		AUDITTYPE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * 保险产品的产品类型前缀（保险的险种取自字典主键，前面加上此标识，以区分）
	 */
	public static final String EX_INS_PRODUCTTYPE = "INSURANCE_";

	/**
	 * 产品类型： 线路类别 +快递 （保险暂时没有列出此枚举，是用字典值加前缀而成，并不对外公开）
	 * 
	 * @author ranlongfei
	 * 
	 */
	public static enum PRODUCTTYPE {

		INNERLINE(1, "国内"), INNERSHORTLINE(2, "国内-短线"), INNERLONGLINE(3,
				"国内-长线"), FOREIGNLINE(4, "出境/港澳台"), EXPRESS(5, "快递"), DEPOSIT(
				6, "押金"), INNER_BORDER_LINE(7, "国内-边境游");

		private int index;
		private String cnName;

		public static String getCnName(String code) {
			for (PRODUCTTYPE item : PRODUCTTYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		public static String getCodeByIndex(int index) {
			for (PRODUCTTYPE item : PRODUCTTYPE.values()) {
				if (item.index == index) {
					return item.getCode();
				}
			}
			return "";
		}

		public static int getIndexByCode(String code) {
			for (PRODUCTTYPE item : PRODUCTTYPE.values()) {
				if (StringUtils.endsWithIgnoreCase(item.name(), code)) {
					return item.index;
				}
			}
			return 0;
		}

		PRODUCTTYPE(int index, String name) {
			this.index = index;
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}

	
	public static enum PRODUCTTOURTYPE {

		ONEDAYTOUR("一日游"),MULTIDAYTOUR("多日游");

		private int index;
		private String cnName;

		public static String getCnName(String code) {
			for (PRODUCTTOURTYPE item : PRODUCTTOURTYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		public static int getIndexByCode(String code) {
			for (PRODUCTTOURTYPE item : PRODUCTTOURTYPE.values()) {
				if (StringUtils.endsWithIgnoreCase(item.name(), code)) {
					return item.index;
				}
			}
			return 0;
		}

		PRODUCTTOURTYPE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
	
	public static enum WIFILINETYPE {
		INNERLINE("国内"),FOREIGNLINE("出境/港澳台");
		private int index;
		private String cnName;

		public static String getCnName(String code) {
			for (WIFILINETYPE item : WIFILINETYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		public static int getIndexByCode(String code) {
			for (WIFILINETYPE item : WIFILINETYPE.values()) {
				if (StringUtils.endsWithIgnoreCase(item.name(), code)) {
					return item.index;
				}
			}
			return 0;
		}

		WIFILINETYPE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}


	public static enum WIFIPRODUCTTYPE {
		WIFI("WIFI"), PHONE("电话卡");

		private int index;
		private String cnName;

		public static String getCnName(String code) {
			for (WIFIPRODUCTTYPE item : WIFIPRODUCTTYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		public static int getIndexByCode(String code) {
			for (WIFIPRODUCTTYPE item : WIFIPRODUCTTYPE.values()) {
				if (StringUtils.endsWithIgnoreCase(item.name(), code)) {
					return item.index;
				}
			}
			return 0;
		}

		WIFIPRODUCTTYPE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
	
	
	public static enum UPDATETYPE{
		PRODUCT("产品基础"), ROUTE("行程修改"),PRICE("库存价格");

		private String cnName;

		public static String getCnName(String code) {
			for (UPDATETYPE item : UPDATETYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		UPDATETYPE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
	
	public static enum REDISMESSAGEKEY{
		PRODUCTUPDATE("产品修改");

		private String cnName;

		public static String getCnName(String code) {
			for (REDISMESSAGEKEY item : REDISMESSAGEKEY.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}

		REDISMESSAGEKEY(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}

		public String getCnName() {
			return this.cnName;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
	
	
	
	public static enum SERVICELANGUAGETYPE {
		CHINA(1,"中文"),ENGLISH(2,"英语"),MALAY(3,"马来语"),THAI(4,"泰语"),
		JAPNESE(5,"日语"),KOREAN(6,"韩语"),ARABIC(7,"阿拉伯语"),SPANISH(8,"西班牙语"),
		PORTUGUESE(9,"葡萄牙语"),FRENCH(10,"法语"),RUSSIAN(11,"俄语"),OTHER(12,"其他");
	
	    private int  index;
		private String cnName;

		public static String getCnName(String code) {
			for (SERVICELANGUAGETYPE item : SERVICELANGUAGETYPE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}
		
		public static int getIndexByCode(String code) {
			for (SERVICELANGUAGETYPE item : SERVICELANGUAGETYPE.values()) {
				if (StringUtils.endsWithIgnoreCase(item.name(), code)) {
					return item.index;
				}
			}
			return 0;
		}
		
		SERVICELANGUAGETYPE(int index,String name) {
			this.index = index;
			this.cnName = name;
		}
		public String getCode() {
			return this.name();
		}
		

		public String getCnName() {
			return this.cnName;
		}
		public int getIndex(){
			return this.index;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}
	
	public static enum SERVICEFEATURE {
		CHINA("中文服务"),FREE("免费等待"),PLACARDS("举牌接机");

	
		private String cnName;

		public static String getCnName(String code) {
			for (SERVICEFEATURE item : SERVICEFEATURE.values()) {
				if (item.getCode().equals(code)) {
					return item.getCnName();
				}
			}
			return code;
		}
		
		
		SERVICEFEATURE(String name) {
			this.cnName = name;
		}

		public String getCode() {
			return this.name();
		}
		

		public String getCnName() {
			return this.cnName;
		}
		
		@Override
		public String toString() {
			return this.name();
		}
	}
	
}