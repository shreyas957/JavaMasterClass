package com.shreyas.array;

import java.util.Arrays;
import java.util.Random;

public class ArraysClass {
    public static void main(String[] args) {
        int[] arr1 = getRandomArray(10);
        System.out.println("arr1 = " + Arrays.toString(arr1));
        Arrays.sort(arr1);
        System.out.println("Sorted arr1 = " + Arrays.toString(arr1));

        int[] arr2 = new int[5];
        Arrays.fill(arr2,10);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = getRandomArray(5);
        System.out.println(Arrays.toString(arr3));
        int[] arr4 = Arrays.copyOf(arr3, arr3.length);
        // Second argument is the size: if size == other size then all array is copied, less-> then from 0 to size is copied, more-> then all array get copied and other elements are zero.
        System.out.println(Arrays.toString(arr4));

        int[] arr5 = new int[]{1,2,3,4,5};
        System.out.println(Arrays.binarySearch(arr5, 3));   //Returns index, array should be sorted.

        int[] s1 = {1,2,3,4,5};
        int[] s2 = {1,2,3,4,5};
        System.out.println(Arrays.equals(s1, s2));      // Array should be of same size, the position of element is also matters.


    }

    private static int[] getRandomArray(int len){      // This function returns any random array.
        Random random = new Random();
        int[] newArray = new int[len];
        for(int i=0;i<len;i++){
            newArray[i] = random.nextInt(100);
        }
        return newArray;
    }
}
