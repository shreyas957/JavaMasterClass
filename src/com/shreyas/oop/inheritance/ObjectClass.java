package com.shreyas.oop.inheritance;

// Every class we create in java is subclass of java.lang.Object. It is done implicitly , but we can do it explicitly too.

public class ObjectClass extends Object{
    public static void main(String[] args) {
        Student shreyas = new Student("Shreyas", 20);
        System.out.println(shreyas);

        PrimaryStudent shreeya = new PrimaryStudent("Shreeya", 19, "Sachin");
        System.out.println(shreeya);
    }
}

class Student extends ObjectClass{
    private String name;
    private int age;


    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


class PrimaryStudent extends Student{
    private String parentName;

    public PrimaryStudent(String parentName, int age, String name1) {
        super(parentName, age);
        this.parentName = name1;
    }

    @Override
    public String toString() {
        return "PrimaryStudent{" +
                "parentName='" + parentName + '\'' +
                "} " + super.toString();
    }
}



//    A hashCode is an integer, that is unique to  an instance (in the currently executing code).
//
//        When an instance is created, it's assigned  a hashCode, and that hashCode is what can
//
//        tell us if our multiple references,  are pointing to a single instance.
//
//        It's a mechanism for comparison, in other words. This really is like an address for a house,