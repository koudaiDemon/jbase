package com.something.bigdemo.db;

import com.something.bigdemo.event.PersistentEnum;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author cWww
 * @Title
 * @Description 模拟数据库Item
 * @date: 2018/9/3  11:43
 */
public class Item<T> implements Serializable {

    private Long pk;
    private PersistentEnum persistentEnum;
    private Collection<String> conditions;
    private T item;
    /**
     * 事务开启默认false
     */
    private boolean isTransaction = false;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public PersistentEnum getPersistentEnum() {
        return persistentEnum;
    }

    public void setPersistentEnum(PersistentEnum persistentEnum) {
        this.persistentEnum = persistentEnum;
    }

    public Collection<String> getConditions() {
        return conditions;
    }

    public void setConditions(Collection<String> conditions) {
        this.conditions = conditions;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isTransaction() {
        return isTransaction;
    }

    public void setTransaction(boolean transaction) {
        isTransaction = transaction;
    }

    @Override
    public String toString() {
        return "Item{" +
                "pk=" + pk +
                ", persistentEnum=" + persistentEnum +
                ", conditions=" + conditions +
                ", item=" + item +
                ", isTransaction=" + isTransaction +
                '}';
    }
}
