package com.shreyas.oop.inheritance;

public class ChildClass extends ParentClass{

    private String childName;

    public ChildClass(){
        super("Shreeya");   // Calls Constructor of parent class.

    }

    public ChildClass(String parentName, String childName) {
        super(parentName);
        this.childName = childName;
    }

    @Override
    public String toString() {
        return "ChildClass{" +
                "childName='" + childName + '\'' +
                "} " + super.toString();
    }

    @Override
    public void hello(int height) {     // Overriding the method of parent clas of same signature
        super.hello(height);
        System.out.println("This is from override method");
    }

    @Override
    public void sayHi() {
        super.sayHi();
        walk();
    }

    private void walk(){
        System.out.println(childName + " is walking");
    }

}
