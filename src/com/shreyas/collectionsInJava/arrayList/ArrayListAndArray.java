package com.shreyas.collectionsInJava.arrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListAndArray {
    public static void main(String[] args) {
        // Array to List :

        String[] originalArray = new String[]{"1st", "3rd"};
        var originalList = Arrays.asList(originalArray);    // var == ArrayList --> mutable
        originalList.set(1, "2nd");
        System.out.println("List: " + originalList);
        System.out.println("Array: " + Arrays.toString(originalArray));
        List<String> originalList1 = List.of(originalArray);    // immutable list


        // List to array :
        ArrayList<String> arrayList = new ArrayList<>(List.of("Shreyas", "Vikas", "Kartik"));
        String[] arrayString = arrayList.toArray(arrayList.toArray(new String[arrayList.size()]));
        System.out.println(Arrays.toString(arrayString));


        ArrayList<String> abc = new ArrayList<>(Arrays.asList("Shreyas"));


    }
}
