package com.cwww.spring;

import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/5/23  9:35
 */
public class TestList {

    private List<Action> list;

    public TestList() {
    }

    public TestList(List<Action> list) {
        this.list = list;
    }

    public List<Action> getList() {
        return list;
    }

    public void setList(List<Action> list) {
        this.list = list;
    }
}
