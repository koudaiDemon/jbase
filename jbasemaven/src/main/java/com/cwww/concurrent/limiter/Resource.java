package com.cwww.concurrent.limiter;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/26  13:35
 */
public abstract class Resource<T> {
    private Integer count = 1;
    private LinkedList<T> queue = new LinkedList<>();
    private Semaphore semaphore = new Semaphore(count);



    public Resource(Integer count){
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
