package com.shreyas.oop.GettersAndSetters;

public class Main {
    public static void main(String[] args) {
        Class_Getter_Setter_Provider car = new Class_Getter_Setter_Provider();     // Created object of class
        car.PrintCarInfo();     // Called the method

        System.out.println("Name " + StaticField.name); // As this field is static no need of instance of class to use it.(Field is Public)

        System.out.println("Company name is " + car.getCompany() + " (by Getter)");

        car.setCompany("Land Rover");   //Setter method is called
        System.out.println("Company name is " + car.getCompany() + " (Changed by Setter)");

        car.setModel("Range Rover");    // Setter method
        car.PrintCarInfo();
    }
}








