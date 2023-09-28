package com.shreyas.generics;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Wildcards {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<Double> list3 = Arrays.asList(1.0, 2.0, 3.0);
        List<Number> list4 = Arrays.asList(new BigDecimal("10.0"), new BigDecimal("10.15"));


        unboundedWildCard(list1);
        unboundedWildCard(list2);

        System.out.println("-".repeat(10));

        upperBoundedWildCard(list2);
        upperBoundedWildCard(list3);

        System.out.println("-".repeat(10));

        lowerBoundedWildCard(list2);
        lowerBoundedWildCard(list4);

    }
    public static void unboundedWildCard(List<?> ls) {
        // If I use Object instead of "?", we fail to print the List for Integer, Double, etc.
        System.out.println(ls);
    }

    public static void upperBoundedWildCard(List<? extends Number> ls) {
        // Only takes Number and it's child's
        System.out.println(ls);
    }

    public static void lowerBoundedWildCard(List<? super Integer> ls) {
        // If we want to use the specific data type or its super types
        System.out.println(ls);
    }
}
