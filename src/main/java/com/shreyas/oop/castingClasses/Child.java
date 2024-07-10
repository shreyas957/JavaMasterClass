package com.shreyas.oop.castingClasses;

public class Child extends Parent{
    public Child(String Name){
        super(Name);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Into child class");
    }
}
