package com.shreyas.string;

public class StringBuilderClass {
    public static void main(String[] args) {
        String helloWorld = "Hello" + " World";
        StringBuilder helloBuilder = new StringBuilder("Hello" + " World");
        printString(helloWorld);
        printStringBuilder(helloBuilder);

        helloWorld.concat(" goodbye");  // This will bw ignored because we didn't assign it to any variable.
        helloBuilder.append(" goodbye");
        printString(helloWorld);
        printStringBuilder(helloBuilder);

        StringBuilder info = new StringBuilder();
        StringBuilder info20 = new StringBuilder(20);
        StringBuilder infoEx = new StringBuilder(helloBuilder);
        printStringBuilder(info);
        printStringBuilder(info20);
        printStringBuilder(infoEx);

    }
    public static void printString(String helloWorld){
        System.out.println(helloWorld);
        System.out.println("Length : " + helloWorld.length());
        System.out.println("\n");
    }
    public static void printStringBuilder(StringBuilder helloBuilder){
        System.out.println(helloBuilder);
        System.out.println("Length : " + helloBuilder.length());
        System.out.println(helloBuilder.capacity());
        System.out.println("\n");
    }
}
