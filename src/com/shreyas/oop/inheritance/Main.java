package com.shreyas.oop.inheritance;

public class Main {
    public static void main(String[] args) {
        ParentClass parent = new ParentClass("Shreyas");
        doStuff(parent, 6);

        ChildClass child1 = new ChildClass();
        doStuff(child1, 5);      // we have passed child but type is ParentClass still works because it is inherited from it.

        ChildClass child2 = new ChildClass("ABC", "XYZ");
        doStuff(child2, 10);
    }

    public static void doStuff(ParentClass parent, int height){     // Passed object as argument of type ParentClass.
        parent.sayHi();
        parent.hello(height);
        System.out.println(parent);
        System.out.println("-----------------------");
    }
}

