package com.shreyas.oop.castingClasses;

public class Parent {
    private String Name;

    public Parent(String Name){
        this.Name = Name;
    }

    public void printInfo(){
        System.out.println("My name is " + Name);
        System.out.println("Into Parent Class");
    }
}
