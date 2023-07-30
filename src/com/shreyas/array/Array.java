package com.shreyas.array;

import java.util.Arrays;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        int[] array1 = new int[5];  //declaration and initialization. add values by iteration.

        int array2[] = {1,2,3,4,5};    // Declaration and initialization at same time. (Anonymous array initializer).

        int[] array3 = new int[]{1,2,3,4,5}; // Another way
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<5;i++){
            array1[i] = sc.nextInt();
        }
        for(int i=0;i<5;i++){
            System.out.println(array1[i]);
        }
        for(int i : array1){
            System.out.println(i);
        }
        System.out.println(Arrays.toString(array1));
        Object obj = array1;
        if(obj instanceof int[]){
            System.out.println("Obj is an integer array");
        }

        Object[] objectArray = new Object[4];   // Array of object.
        objectArray[0] = "Shreyas";
        objectArray[1] = 10;
        objectArray[2] = new StringBuilder("Hello");
        objectArray[3] = array1;  //new int[5] is also valid but we dont have the name of it.


        // I can create and object array too.



    }
}
