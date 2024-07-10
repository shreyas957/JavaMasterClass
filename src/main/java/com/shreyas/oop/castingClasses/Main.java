package com.shreyas.oop.castingClasses;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent("Manisha");
        parent.printInfo();
        System.out.println("-----------------");

        Parent parent1 = new Child("Shreyas");      // Upcasting, done implicitly
        parent1.printInfo();
        System.out.println("------------------");

//        Child child = (Child) new Parent("Sanjay");       // This will not work as down casting
        Parent parent2 = new Child("Shreeya");
        Child child = (Child) parent2;              // Down casting
        child.printInfo();
        System.out.println("------------------");

        var obj = new Parent("Sam");     // Var automatically decides the type of object.
        obj.printInfo();
        System.out.println("------------------");

        System.out.println(obj instanceof Parent);
        System.out.println(obj instanceof Child);

    }
}
