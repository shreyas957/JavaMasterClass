package com.shreyas.exceptions;

public class CustomExceptionWithParameter {
    public static void main(String[] args) throws Exception {
        ExceptionParameter(-19);
    }
    public static void ExceptionParameter(int age) throws Exception {
        if(age < 0){
            throw new Exception("Oh no!!, Something went wrong");
            // This one does not need other class because "Exception"
            // it is itself a class so, we directly passed the string.
        }
    }
}
