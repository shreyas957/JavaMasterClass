package com.shreyas.exceptions;

public class NegativeAgeCustomException extends Exception{
    // extends Exception --> checked Exception
    // extends RuntimeException --> unchecked Exception.
    //here we need to make a constructor to get the passed string from the exception
    // If I extend this class as "RuntimeException" then I don't need to throw an exception in method were the exception is called.
}