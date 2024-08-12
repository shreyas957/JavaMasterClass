package com.shreyas.oop.POJOAndRecord;

import java.io.Serializable;

public class JavaBean implements Serializable {
    private String name;
    private int age;

    public JavaBean() {
    }

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
