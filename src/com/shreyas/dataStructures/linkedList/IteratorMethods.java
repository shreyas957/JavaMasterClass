package com.shreyas.dataStructures.linkedList;

import java.util.LinkedList;

public class IteratorMethods {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        names.add("Shreyas");
        names.add("Kartik");
        names.add("Vikas");
        names.add("Sumit");
        names.add("Sumit");

        var iterator = names.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
//            if(iterator.next().equals("Sumit")){
//                iterator.remove();
//            }
        }



    }
}
