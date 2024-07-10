package com.shreyas.oop.inheritance;

public class ParentClass {
    private String parentName = "Shreyas";

    public ParentClass() {

    }

    public ParentClass(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "ParentClass{" +
                "Name='" + parentName + '\'' +
                '}';
    }

    public void hello(int height){
        System.out.println("Height of " + parentName + " is " + height);
    }
    public void sayHi(){
        System.out.println("Hii! " + parentName);
    }
}
