package com.shreyas.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingWithStreams {
    public static void main(String[] args) {
        imperativeApproach();
        declarativeApproach();
    }

    public static void imperativeApproach() {
        // Count how many people have age more than 10 (take only 1st 10)and save in another list
        List<Integer> age = Arrays.asList(2, 10, 15, 23, 67, 4, 35, 47, 8, 19, 27, 98, 25, 42, 21, 35);
        List<Integer> ans = new ArrayList<>();
        int counter = 0;
        for (var person : age) {
            if (person > 10) {
                ans.add(person);
                counter++;
                if (counter == 10) {
                    break;
                }
            }
        }
        System.out.println(ans);
    }

    public static void declarativeApproach() {
        List<Integer> age = Arrays.asList(2, 10, 15, 23, 67, 4, 35, 47, 8, 19, 27, 98, 25, 42, 21, 35);
        List<Integer> ans = age.stream(). //--> abstraction on concrete class
                filter(a -> a > 10).limit(10)   // --> intermediate operators (Can have as many as I want)
                .collect(Collectors.toList());  // --> Terminal operator (Can have only one)

        age.add(100); // This will not affect the ans list (Streams are immutable)
        ans.add(100);
//        Stream<Integer> ageStream = age.stream();
//        Stream<Integer> integerStream = ageStream.filter(a -> a > 10);
//        Stream<Integer> limit = integerStream.limit(10);
//        List<Integer> list = limit.toList();
        System.out.println(ans);
    }
}

// How to use java streams
// 1. Start with a collection
// 2. Convert the collection to stream
// 3. Use intermediate operators to transform the stream
// 4. Use terminal operator to get the result
// 5. Streams are lazy, they don't do anything until we call terminal operator
// 6. Streams are immutable, they don't change the original collection
// 7. Streams are ordered, they maintain the order of the elements in the collection
// 8. Streams are reusable, we can use the same stream multiple times
// 9. Streams are short-circuiting, they can stop processing the elements if they find the result
// 10. Streams are parallelizable, they can run in parallel eg parallelStream() method
// 11. Streams are declarative, we don't have to worry about how to do it, we just have to say what to do
// 12. Streams are composable, we can chain multiple operations together
// 13. Streams are functional, we can use lambda expressions to define the operations