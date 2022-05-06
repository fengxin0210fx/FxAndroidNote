package com.fx.note.bean;

/**
 * @author Created by 冯鑫 on 2021/9/14 16:57.
 * @description
 */
public class Pig {

    private String name;
    private int age;

    public Pig(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
