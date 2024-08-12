package com.shreyas.basics;

// cant use float , long , double, boolean or their wrappers in switch case.

public class switchCase {
    public static void main(String[] args) {
        String val = "old";
        switch (val) {
            case "new":
                System.out.println("it is new switch statement");
                break;
            case "old": case "nope":   //*  any of this true then block will be executed.
                System.out.println("it is old switch statement");
                break;
            default:
                System.out.println("No matching value for Val");
                break;
        }
        enhancedSwitch();
        switchExpression();
        System.out.println(switchExpressionYield());

    }

    public static void enhancedSwitch(){
        int val = 10;
        switch(val){
            case 11 -> System.out.println("Value is 11");
            case 10 -> System.out.println("Value is 10");
            default -> throw new IllegalStateException("Unexpected value: " + val);
        }
    }

    public static void switchExpression(){
        String day = "Monday";
        String result = "";
        result = switch(day){
            case "Sunday" -> "10 AM";
            case "Monday" -> "6 AM";
            default -> "7 AM";
        };

        System.out.println("Time for " + day + " is " + result);
    }

    public static String switchExpressionYield(){
        String day = "Monday";
        String result = "";
        // We can assign this expression return value to a variable or directly return to method.
        return switch(day){
            case "Sunday" : { yield "10 AM"; }
            case "Monday" : { yield "7 AM"; }
            default: { yield "8 AM"; }
        };
    }
}


