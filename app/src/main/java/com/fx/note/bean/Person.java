package com.fx.note.bean;

/**
 * @author Created by 冯鑫 on 2021/9/10 16:10.
 * @description
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
