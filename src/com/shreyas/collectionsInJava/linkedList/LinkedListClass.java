package com.shreyas.collectionsInJava.linkedList;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListClass {
    public static void main(String[] args) {
        LinkedList<String> places = new LinkedList<>();     // var places = new LinkedList<String>();
        places.add("Kolhapur");
        places.add(0, "Mumbai");
        System.out.println(places);

        addMorePlaces(places);
        System.out.println(places);
        System.out.println("-".repeat(20));

        removeMorePlaces(places);
        System.out.println(places);
        System.out.println("-".repeat(20));

        retrievalMethods(places);
        System.out.println("-".repeat(20));

        iteration(places);
    }
    public static void addMorePlaces(LinkedList<String> temp){
        temp.addFirst("Home");
        temp.addLast("USA");
        //Queue methods:
        temp.offer("Delhi");
        temp.offerFirst("Kedarnath");
        temp.offerLast("Odisha");
        //Stack methods:
        temp.push("Pune");
    }

    public static void removeMorePlaces(LinkedList<String> temp){
        temp.remove(1);
        temp.remove("USA");

        /* we also have removeFirst, removeLast, removeFirstOccurrence, removeLastOccurrence,
           Queue -> poll, pollFirst, pollLast
           Stack -> pop
        */
    }

    public static void retrievalMethods(LinkedList<String> temp){
        System.out.println(temp.get(1));  //getFirst and getLast
        System.out.println(temp.indexOf("Home")); //LastIndexOf
        // Queue ->
        System.out.println(temp.element()); // return 1st item

        // Stack :
        System.out.println(temp.peek());
        System.out.println(temp.peekFirst());
        System.out.println(temp.peekLast());

    }

    public static void iteration(LinkedList<String> temp){
        for(int i = 0; i < temp.size(); i++){
            System.out.print(temp.get(i) + " ");
        }
        System.out.println();

        for(var it : temp){
            System.out.print(it + " ");
        }
        System.out.println();
        // List Iterator :
        ListIterator<String> iterator = temp.listIterator();    // We can pass starting index of iteration in listIterator()
        while (iterator.hasNext()){
            String val = iterator.next();
            System.out.print(val + " ");
        }
    }
}
