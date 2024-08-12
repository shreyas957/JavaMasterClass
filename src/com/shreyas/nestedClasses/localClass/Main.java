package com.shreyas.nestedClasses.localClass;

public class Main {
    public static void main(String[] args) {
        // Creating an instance of the outer class
        LocalClass outerObj = new LocalClass();

        // Calling the outer class method, which in turn creates and uses a local class
        outerObj.outerMethod();
    }
}
