package com.shreyas.oop.constructor;

public class Main {
    public static void main(String[] args) {
        ConstructorClass callConstructor = new ConstructorClass();       // Calling Constructor.
        System.out.println(callConstructor.getCustomerName());
        System.out.println(callConstructor.getPhone());

        ConstructorClass customerInfo2P = new ConstructorClass("Shreyas Sarkar", "9657796022"); // with parameters
        System.out.println(customerInfo2P.getCustomerName());
        System.out.println(customerInfo2P.getPhone());

        ConstructorClass customerInfo1P = new ConstructorClass("Sanjay Sarkar");
        System.out.println(customerInfo1P.getCustomerName());
        System.out.println(customerInfo1P.getPhone());
    }

}
