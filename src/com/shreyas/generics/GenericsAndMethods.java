package com.shreyas.generics;

public class GenericsAndMethods {
    public static void main(String[] args) {
        String[] names = {"Shreyas", "Shreeya"};
        Character[] letters = {'A', 'B'};
        print(names);
        print(letters);
    }

    // With Generics : Method will take any parameter
    static <T> void print(T[] array) {
        for (T e : array) {
            System.out.println(e.getClass().getName() + "-" + e);
        }
    }

    // Without Generics --> Only defined type will be taken, here only String array
    static void print2(String[] array) {
        for (String e : array) {
            System.out.println(e.getClass().getName() + "-" + e);
        }
    }

    static <T> T print3(T t) {
        System.out.println(t);
        return t;
    }
}
