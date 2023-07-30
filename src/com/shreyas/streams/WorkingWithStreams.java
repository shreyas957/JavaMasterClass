package com.shreyas.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkingWithStreams {
    public static void main(String[] args) {
        imperativeApproach();
        declarativeApproach();
    }
    public static void imperativeApproach() {
        // Count how many people have age more than 10 and save in another list
        List<Integer> age = Arrays.asList(2, 10, 15, 23, 67, 4, 35, 47, 8, 19, 27, 98, 25, 42, 21, 35);
        List<Integer> ans = new ArrayList<>();
        int counter = 0;
        for (var person : age) {
            if(person > 10) {
                ans.add(person);
                counter++;
                if(counter == 10) {
                    break;
                }
            }
        }
        System.out.println(ans);
    }

    public static void declarativeApproach() {
        List<Integer> age = Arrays.asList(2, 10, 15, 23, 67, 4, 35, 47, 8, 19, 27, 98, 25, 42, 21, 35);
        List<Integer> ans = age.stream(). //--> abstraction on concrete class
                filter(a -> a > 10).limit(10)     // --> intermediate operators (Can have as many as I want)
                .toList();  // --> Terminal operator

        System.out.println(ans);


    }
}
