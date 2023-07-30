package com.shreyas.array;

import java.util.Arrays;
import java.util.Scanner;

public class NestedArray {
    public static void main(String[] args) {
        int[][] arr = new int[3][3];    // first square brackets will give array length, second square brackets will give length of each inner array.
        Scanner sc = new Scanner(System.in);

        for(int i=0;i<arr.length;i++){  //Iterate over outer length
            int[] tempArr = arr[i];    // assign each inner array to this temp
            for(int j=0;j<tempArr.length;j++){  // Iterate over inner array
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        for (int[] ints : arr) {
            //Iterate over outer length --> This can also be used  for(int i=0;i<arr.length;i++){sout(Arrays.toString(arr[i]))}
            System.out.println(Arrays.toString(ints));
        }
        for(var outer : arr){   //here var == int[].
            for(var inner : outer){ // here var == int.
                System.out.print(inner + " ");
            }
            System.out.println();
        }
        System.out.println(Arrays.deepToString(arr));   // Method to print multidimensional array.

        System.out.println("-".repeat(20));

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(Arrays.deepToString(arr));

        Object[] obj = new Object[3];

        obj[0]= new int[][]{  //or [3][] is also valid because outer size is fixed.
                {1,2,3},
                {1,49,18,12},
                {12,232,21,34,45,10}
        };
        obj[1] = new String[]{"Shreyas", "Shreeya"};
        System.out.println(Arrays.deepToString(obj));


    }
}
