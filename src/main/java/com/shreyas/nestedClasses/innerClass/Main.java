package com.shreyas.nestedClasses.innerClass;

public class Main {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();

        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.print();
    }
}
