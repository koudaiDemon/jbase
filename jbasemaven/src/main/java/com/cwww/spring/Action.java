package com.cwww.spring;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/5/23  9:51
 */
public class Action {

    private String name;
    private Integer level;

    public Action() {
    }

    public Action(String name, Integer level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
