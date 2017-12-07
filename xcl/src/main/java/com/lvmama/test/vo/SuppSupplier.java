package com.lvmama.test.vo;

import java.io.Serializable;

public class SuppSupplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3537349184143407853L;

	private Long supplierId;

	private String supplierName;

	private String bu;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}
	
	// 合同结算BU
    public static enum CONTRACT_SETTLE_BU {
        LOCAL_BU("国内度假事业部（跟团游事业部）"),
        LOCAL_TRAFFIC_BU("国内度假事业部（大交通事业部）"),
        DESTINATION_BU("国内度假事业部（酒店自由行事业部）"),
        OUTBOUND_BU("出境游事业部"),
        TICKET_BU("景区玩乐事业群"),
        BUSINESS_BU("商旅定制事业部"),
        O2OWUXI_BU("O2O无锡子公司"),
        O2ONINGBO_BU("O2O宁波子公司");
        private String cnName;

        public static String getCnName(String code) {
            for (CONTRACT_SETTLE_BU item : CONTRACT_SETTLE_BU.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        CONTRACT_SETTLE_BU(String name) {
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

	@Override
	public String toString() {
		return "SuppSupplier[supplierId="+supplierId+"+supplierName="+supplierName+"bu="+bu+"]";
	}

	@Override
	public boolean equals(Object obj) {
		final SuppSupplier s = (SuppSupplier) obj;
		if(this.supplierId.equals(s.supplierId)){
			return true;
		}else{
			return false;
		}
	}

	
}
