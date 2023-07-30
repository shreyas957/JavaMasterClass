package com.shreyas.dataStructures.arrayList;

import java.util.ArrayList;
import java.util.Arrays;

record Items(String name, int cnt){
    public Items(String name){ this(name, 1); }
}

public class ArraylistClass {
    public static void main(String[] args) {
        Object[] groceryList = new Object[3];
        groceryList[0] = new Items("Milk", 2);
        groceryList[1] = new Items("Cream");
        groceryList[2] = "Chips";
        System.out.println(Arrays.toString(groceryList));

        ArrayList arrayObj = new ArrayList();   // Raw use of parameterized ArrayList class : Acts as Object class array == groceryList..
        arrayObj.add(new Items("Milk", 10));
        arrayObj.add("Chocolate");
        System.out.println(arrayObj);

        ArrayList<Items> groceryList1 = new ArrayList<>();
        groceryList1.add(new Items("Juice", 10));
        groceryList1.add(0, new Items("Cake", 2));
//        groceryList1.add("Milk Powder");  will give error because type is Items and we are giving string
        System.out.println(groceryList1);


        ArrayList<ArrayList<String>> multiDList = new ArrayList<>();

    }
}


/*
  Methods in ArrayList :
  1. add() : can add at any specific index
  2. addAll() : This method is used to append all the elements from a specific collection to the end of the mentioned list
  3. clear() : remove all elements
  4. clone() : returns shallow copy
  5. contains() : returns boolean
  6. isEmpty(), get(), indexOf(), lastIndexOf()--> index of last occurrence of element
   etc....
 */



