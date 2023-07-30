package com.shreyas.exceptions;

/*
   Exceptions are classes, so we can make our custom exception my making a class and implement it
*/
public class CustomException{
    public static void main(String[] args) throws NegativeAgeCustomException{
        validAge(-1);
    }
    public static void validAge(int age) throws NegativeAgeCustomException {
        if(age < 0){
            throw new NegativeAgeCustomException();
            // [ We can also pass parameter to above custom exception of validAge
            // but in the NegativeAgeCustomException Exception-class,
            // we have to make a constructor to take that parameter. ]
        }
    }
}

