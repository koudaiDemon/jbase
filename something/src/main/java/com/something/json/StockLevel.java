package com.something.json;

import java.io.Serializable;

/**
 * 库存信息
 * Created by jessen on 15/7/16.
 */
public class StockLevel implements Serializable {
	private String productCode; //sku编码
	private String pointOfService; //服务点名称
	private int available; //现有量
	private int reserved; //保留量
	private int frozen; //冻结量

    // Add by Bao@20160122
    private boolean replaceOperation; // 库存替换操作

    // Add by cwww at 2018/6/12
    private String modelFlag; //类型+PK标识model

    public String getModelFlag() {
        return modelFlag;
    }

    public void setModelFlag(String modelFlag) {
        this.modelFlag = modelFlag;
    }

    public boolean getReplaceOperation() {
        return replaceOperation;
    }

    public void setReplaceOperation(boolean replaceOperation) {
        this.replaceOperation = replaceOperation;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPointOfService() {
        return pointOfService;
    }

    public void setPointOfService(String warehouse) {
        this.pointOfService = warehouse;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

	public int getFrozen() {
		return frozen;
	}

	public void setFrozen(int frozen) {
		this.frozen = frozen;
	}

	public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}
