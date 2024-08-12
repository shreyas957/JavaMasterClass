package com.shreyas.exceptions;

public class ExceptionHandlingTryCatch {
    public static void main(String[] args){
        tryCatch();
    }
    public static void tryCatch() {
        // I am assuming that there may be exception will occur while doing parseInt,
        // Because user may give a string which cant be parsed to int, and it will give "NumberFormatException".
        // So to avoid abrupt termination of our program we will try to catch an exception.
        String num = "shreyas";
        try {
            System.out.println(Integer.parseInt(num));          // Statement that may throw exception
        } catch (Exception e) {
            System.out.println("String is invalid");    // Code we want to execute if exception occur
        }finally {
            System.out.println("This will execute with or without exception");
        }
        System.out.println("End of program");
    }
}
