package com.shreyas.generics;

public class BoundedTypeParameters {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 0, 5};
        System.out.println(countGreaterThan(numbers, 2));

        Double[] numbers2 = {1.0, 2.0, 3.0, 5.0};
        System.out.println(countGreaterThan(numbers2, 2.5));
    }

    // Bounded type parameter
    static <T extends Comparable<T>> int countGreaterThan(T[] numbers, T num) {
        int cnt = 0;
        for (T n : numbers) {
            if (n.compareTo(num) > 0) {
                cnt++;
            }
        }
        return cnt;
    }

    interface A{}
    interface B{}

    // Multiple bounded parameters
    static <T extends Comparable<T> & A & B> void example() {

    }

    class Xyz<T extends Number & A & B> {
        T obj;
    }
}
