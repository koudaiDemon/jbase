package com.something.cache;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/8/30  10:31
 */
public class Dog {

    private long length;
    private String name;
    private long age;

    public Dog() {
    }

    public Dog(long length, String name, long age) {
        this.length = length;
        this.name = name;
        this.age = age;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "length=" + length +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
