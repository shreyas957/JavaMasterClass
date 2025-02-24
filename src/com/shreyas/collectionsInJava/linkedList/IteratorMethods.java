package com.shreyas.collectionsInJava.linkedList;

import java.util.LinkedList;
import java.util.ListIterator;

public class IteratorMethods {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        names.add("Shreyas");
        names.add("Kartik");
        names.add("Vikas");
        names.add("Sumit");

        var iterator = names.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
            if(iterator.next().equals("Sumit")){        // As we are using next it will skip every 2nd element.
                iterator.remove();
            }
        }
        System.out.println();

        ListIterator<String> it = names.listIterator(names.size());
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }

    }
}
