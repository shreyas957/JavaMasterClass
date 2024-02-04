package com.shreyas.collectionsInJava.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        String[] items = {"apples", "bananas", "Milk", "eggs"};

        List<String> list = List.of(items); // of() method transforms array of strings to list (ArrayList)  of strings
        System.out.println(list);
        System.out.println(list.getClass().getName());      // List is immutable means can't add the element.
        System.out.println("-".repeat(20));

        ArrayList<String> arrayList = new ArrayList<>(list);
        arrayList.add("chips");
        System.out.println(arrayList);

        System.out.println("Third item: " + arrayList.get(2));

        System.out.println(arrayList.contains("Milk"));

        arrayList.retainAll(List.of("apples"));     // retains apples only and delete everything else
        System.out.println(arrayList);
        arrayList.clear();
        System.out.println("-".repeat(20));

        arrayList.addAll(List.of("apples", "milk"));
        arrayList.addAll((Arrays.asList("eggs", "ham")));
        System.out.println(arrayList);
        arrayList.sort(Comparator.naturalOrder());  //Comparator special type allows instances to be compare with each other
        System.out.println(arrayList);
        arrayList.sort(Comparator.reverseOrder());
        System.out.println(arrayList);
        System.out.println("-".repeat(20));








    }
}
