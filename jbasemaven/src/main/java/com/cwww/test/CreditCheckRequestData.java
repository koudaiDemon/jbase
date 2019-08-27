package com.cwww.test;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author cWww
 * @Title CreditCheckRequestData
 * @Description 额度检验请求数据
 * @date: 2018/11/2  13:56
 */
public class CreditCheckRequestData implements Serializable {
    @JSONField(name = "ZTTSDKHEDCX")
    private CreditCheckRequestBodyData creditCheckRequestBodyData;

    public CreditCheckRequestData() {
    }

    public CreditCheckRequestData(CreditCheckRequestBodyData creditCheckRequestBodyData) {
        this.creditCheckRequestBodyData = creditCheckRequestBodyData;
    }

    public CreditCheckRequestBodyData getCreditCheckRequestBodyData() {
        return creditCheckRequestBodyData;
    }

    public void setCreditCheckRequestBodyData(CreditCheckRequestBodyData creditCheckRequestBodyData) {
        this.creditCheckRequestBodyData = creditCheckRequestBodyData;
    }

    public static class CreditCheckRequestBodyData {
        @JSONField(name = "VKORG")
        private String orgUid;
        @JSONField(name = "KUNNR")
        private String unit;
        @JSONField(name = "SGMNT")
        private String unitUid;
        @JSONField(name = "WAERS")
        private String currency;
        @JSONField(name = "ZKBETR")
        private String totalPrice;
        @JSONField(name = "ZTYPE")
        private String paymentMode;

        public CreditCheckRequestBodyData() {
        }

        public CreditCheckRequestBodyData(String orgUid, String unit, String currency, String unitUid, String totalPrice, String paymentMode) {
            this.orgUid = orgUid;
            this.unit = unit;
            this.currency = currency;
            this.unitUid = unitUid;
            this.totalPrice = totalPrice;
            this.paymentMode = paymentMode;
        }

        public String getOrgUid() {
            return orgUid;
        }

        public void setOrgUid(String orgUid) {
            this.orgUid = orgUid;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getUnitUid() {
            return unitUid;
        }

        public void setUnitUid(String unitUid) {
            this.unitUid = unitUid;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }
    }
}
