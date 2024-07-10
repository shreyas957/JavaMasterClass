package com.shreyas.nestedClasses.localClass;

public class LocalClass {

    // Outer class variable
    private int outerVariable = 10;

    // Outer class method
    public void outerMethod() {
        // Local class defined within the method
        class LocalClassExample {
            // Local class variable
            private int localVariable = 20;

            // Local class method
            public void display() {
                System.out.println("Outer variable: " + outerVariable);
                System.out.println("Local variable: " + localVariable);
            }
        }

        // Creating an instance of the local class
        LocalClassExample localObj = new LocalClassExample();

        // Calling the local class method
        localObj.display();
    }


}
