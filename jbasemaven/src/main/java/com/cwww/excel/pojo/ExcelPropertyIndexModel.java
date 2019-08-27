package com.cwww.excel.pojo;

import com.alibaba.excel.annotation.ExcelColumnNum;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/26  19:52
 */
public class ExcelPropertyIndexModel extends BaseRowModel {

    @ExcelColumnNum(format = "name",value = 1)
//    @ExcelProperty
    private String name;
    @ExcelColumnNum(format = "password",value = 2)
//    @ExcelProperty
    private String password;

    public ExcelPropertyIndexModel() {
        super();
    }

    public ExcelPropertyIndexModel(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
