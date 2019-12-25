package com.something.mahout.model;

/**
 * @author cWww
 * @Title Gender
 * @Description 性别
 * @date: 2019/10/11  14:59
 */
public enum Gender {

    /**
     * 男性
     */
    M("MALE"),
    /**
     * 女性
     */
    F("FEMALE");

    /**
     * code值
     */
    private String code;

    Gender(String code){
        this.code = code.intern();
    }

    public String getCode(){
        return this.code;
    }

}
