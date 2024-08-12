package com.shreyas.oop.constructor;

public class ConstructorClass {

    private String customerName;
    private String Phone;

    //This is constructor
    public ConstructorClass(){
        // Calling another constructor of 2 parameters from this constructor.
        this("Default Name", "default Phone");
        System.out.println("Constructor is created");
    }
    public ConstructorClass(String customerName) {
        this(customerName, "999999999");    // Calling constructor of 2 parameters.
    }
    public ConstructorClass(String customerName, String mobile){
        // Constructor with parameter
        this.customerName = customerName;
        Phone = mobile;
        //Both ways with and without "this" can be used depend on argument and field name.
    }

    // Getters:
    public String getCustomerName(){
        return customerName;
    }
    public String getPhone(){
        return Phone;
    }
}
