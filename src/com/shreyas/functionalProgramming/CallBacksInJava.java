package com.shreyas.functionalProgramming;

import java.util.function.Consumer;

// CallBacks in Java means passing a function as an argument to another function.
public class CallBacksInJava {
    public static void main(String[] args) {
        hello("Shreyas", "Shevale", val -> {
            System.out.println("No last Name provided for " + val);
        });
        hello("Shreeya", null, val -> {
            System.out.println("No last name for" + val);
        });
    }
    static void hello(String firstName, String lastName, Consumer<String>callBack) {
        System.out.println(firstName);
        if(lastName == null) {
            callBack.accept(firstName);
        }
        else {
            System.out.println(lastName);
        }
    }
}
