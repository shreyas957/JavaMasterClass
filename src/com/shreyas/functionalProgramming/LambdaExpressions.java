package com.shreyas.functionalProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class LambdaExpressions {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of("Shreyas", "Sanjay"));
        names.forEach(s -> System.out.println(s));

        names.forEach(s -> {
            // local
            char c = s.charAt(0);
            System.out.println(s + " means " + c);
        });

        System.out.println("-".repeat(20));

        List<Double[]> coordinates = new ArrayList<>(Arrays.asList(
                new Double[]{31.23, -13.34},
                new Double[]{11.11, -23.34},
                new Double[]{78.34, 293.34}
        ));
//        coordinates.forEach(t -> System.out.println(t[0] + " And " + t[1]));
        BiConsumer<Double, Double> biConsumer = (a, b) -> System.out.println("lag : " + a + ", Lat : " + b);
//        for (Double[] d : coordinates) {
//            processCoo(d[0], d[1], biConsumer);
//        }
        coordinates.forEach(s -> processCoo(s[0], s[1], biConsumer));
        System.out.println("-".repeat(20));
        coordinates.forEach(s -> processCoo(s[0], s[1], (a, b) -> System.out.println("lag : " + a + ", Lat : " + b)));
        System.out.println("-".repeat(20));

        // Predicate example
        coordinates.removeIf(d -> Arrays.deepEquals(d, new Double[]{11.11, -23.34}));
        coordinates.forEach(s -> processCoo(s[0], s[1], biConsumer));

        // Unary Operator
        names.replaceAll(s -> s.toUpperCase());
        names.forEach(s -> System.out.println(s));

        int[] arr = {0,0,0,0,0};
        Arrays.setAll(arr, i -> arr[i] = i);
        System.out.println(Arrays.toString(arr));


    }

    public static <T> void processCoo(T t1, T t2, BiConsumer<T, T> biConsumer) {
        biConsumer.accept(t1, t2);
    }
}
