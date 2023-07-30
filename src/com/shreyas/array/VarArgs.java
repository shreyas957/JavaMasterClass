package com.shreyas.array;

public class VarArgs {
    public static void main(String... args) {
        String s = "Hello world again";
        System.out.println(s);
        String[] splitS = s.split(" "); // Split String at space.
        printTxt(splitS);
        System.out.println("-".repeat(10));
        printTxt("Hello");
        System.out.println("-".repeat(10));
        printTxt("Hello", "World", "Again");
        System.out.println("-".repeat(10));

        String[] sArray = {"Shreyas", "Shreeya"};
        System.out.println(String.join(",", sArray));
        printTxt(sArray);

    }
    public static void printTxt(String... textList){
        for(String t : textList){
            System.out.println(t);
        }
    }
}
