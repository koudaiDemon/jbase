package com.cwww.test;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author cWww
 * @Title CreditCheckResponseData
 * @Description 额度检验返回数据
 * @date: 2018/11/2  13:57
 */
public class CreditCheckResponseData implements Serializable {

    @JSONField(name = "ZTTSDKHEDJG")
    private CreditCheckResponseBodyData responseBodyData;

    public CreditCheckResponseData() {
    }

    public CreditCheckResponseData(CreditCheckResponseBodyData responseBodyData) {
        this.responseBodyData = responseBodyData;
    }

    public CreditCheckResponseBodyData getResponseBodyData() {
        return responseBodyData;
    }

    public void setResponseBodyData(CreditCheckResponseBodyData responseBodyData) {
        this.responseBodyData = responseBodyData;
    }

    public static class CreditCheckResponseBodyData {
        @JSONField(name = "VKORG")
        private String orgUid;
        @JSONField(name = "KUNNR")
        private String unit;
        @JSONField(name = "SGMNT")
        private String credit;
        @JSONField(name = "WAERS")
        private String currency;
        @JSONField(name = "ZKBETR")
        private String totalPrice;
        @JSONField(name = "ZTYPE")
        private String paymentMode;
        @JSONField(name = "PASSED")
        private String passed;
        @JSONField(name = "ZKBETR_AVA")
        private String availableTotal;

        public CreditCheckResponseBodyData() {
        }

        public CreditCheckResponseBodyData(String orgUid, String unit, String credit, String currency, String totalPrice, String paymentMode, String passed, String availableTotal) {
            this.orgUid = orgUid;
            this.unit = unit;
            this.credit = credit;
            this.currency = currency;
            this.totalPrice = totalPrice;
            this.paymentMode = paymentMode;
            this.passed = passed;
            this.availableTotal = availableTotal;
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

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
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

        public String getPassed() {
            return passed;
        }

        public void setPassed(String passed) {
            this.passed = passed;
        }

        public String getAvailableTotal() {
            return availableTotal;
        }

        public void setAvailableTotal(String availableTotal) {
            this.availableTotal = availableTotal;
        }
    }
}
