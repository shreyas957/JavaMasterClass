package com.shreyas.generics;

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
}
