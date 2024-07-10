package com.shreyas.basics;

public class Methods {
    public static void main(String[] args) {
        int a = 100;
        int b = 300;
        boolean state = true;
        calculate(a, b, state);
        System.out.println("Result = " + calculate1(a, b, state));

        System.out.println("Area of Rectangle : " + getArea(2, 5));
        System.out.println("Area of Circle : " + getArea(10));

    }

     //* Method without return statment
     public static void calculate(int x, int y, boolean condition){
        // void -> return nothing
        // public -> access anywhere
        if(condition == true){
            System.out.println("Result = "+ (x+y));
        }

    }

    //* Method with return statment
    public static int calculate1(int x, int y, boolean condition){
        if(condition == true){
           return (x+y);
        }
        return 0;
    }

    //* Method Signature Example : Used two same name of methods but as arguments are diffetent did not get the error
    public static int getArea(int length, int bredth){
        return length*bredth;
    }
    
    public static double getArea(int radius){
        return 3.14*radius*radius;
    }
    
    //* Method Overloading -->Some Valid cases--

    public static void doSomething(int A){
        //method body
    }
    public static int doSomething(float A){
        //method body
        return 0;
    }
    public static void doSometing(int A, float b){
        //method body
    }
    public static void doSomething(float A, int B){
        //method body
    }

}
