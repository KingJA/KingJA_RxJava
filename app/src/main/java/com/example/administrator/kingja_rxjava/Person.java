package com.example.administrator.kingja_rxjava;

/**
 * Description：TODO
 * Create Time：2016/9/23 14:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Person {
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
