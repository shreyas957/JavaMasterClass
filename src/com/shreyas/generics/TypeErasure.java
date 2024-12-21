package com.shreyas.generics;

import java.util.List;

public class TypeErasure {
    public static void main(String[] args) {
        Integer num1 = 10;
        Double num2 = 10.11;
        String num3 = "10";
        print(num1);
        print(num2);
        print(num3);
    }

    public static <T> void print(T t) {
        System.out.println(t + " is of type " + t.getClass().getName());
    }

//    public static void print(List<String> t) {
//        System.out.println(t + " is of type " + t.getClass().getName());
//    }
//
//    public static void print(List<Integer> t) {
//        System.out.println(t + " is of type " + t.getClass().getName());
//    }

    // solution to above problem :
    public static void printList(List<?> t) {
        //  we can now check instance of List<String> and List<Integer> as well
        if(t.get(0) instanceof String temp){
            System.out.println(temp + " is of type " + t.getClass().getName());
        }
        System.out.println(t + " is of type " + t.getClass().getName());
    }
}
