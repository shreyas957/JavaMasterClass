package com.shreyas.generics;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        // Without generics
        List numbers = new ArrayList();
        numbers.add(1);
        numbers.add("2");
        try {
            for(Object number : numbers) {
                System.out.println((int) number);   // Throws ClassCastException
            }
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }


        // With generics
        List<Integer> numbers1 = new ArrayList<>();     // Makes Type-safe
        numbers1.add(1);
        numbers1.add(2);
        for(Object number : numbers1) {
            System.out.println((int) number);   // Throws ClassCastException
        }


        Comparable num = 10;    // Not type safe
        try {
            num.compareTo("10");    // Throws ClassCastException
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

        // Using Generics we make it type safe at compile time
        Comparable<Integer> num1 = 10;
        System.out.println(num1.compareTo(10));

    }
}
