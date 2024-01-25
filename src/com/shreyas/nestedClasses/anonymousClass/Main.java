package com.shreyas.nestedClasses.anonymousClass;


public class Main {
    public static void main(String[] args) {

        // Using anonymous class
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("Hello");
            }
        };
        greeting.greet();
    }
}
