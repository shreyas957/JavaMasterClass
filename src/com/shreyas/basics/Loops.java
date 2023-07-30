package com.shreyas.basics;
/**
    In java loops are same as CPP.
    Break and Continue are also used in looping.
    1. for specific condition if I used break keyword then if condition is true then loop will be stopped.
       and completely ignores the statements presents in loop block.
    2. Continue statement in its simplest form will stop executing current iteration and start executing next iteration.
 **/

public class Loops {
    public static void main(String[] args) {
        ForLoop();
        WhileLoop();
        DoWhileLoop();
        enhancedForLoop();
    }
    public static void ForLoop(){
        System.out.println("For Loop");
        int i;
        for(i=0;i<10;i++){
            System.out.println(i);
        }
    }
    public static void enhancedForLoop(){       // For-each loop
        int[] array = {1, 2, 3, 4};
        for(int i : array){
            System.out.println(i);
        }
    }
    public static void WhileLoop(){
        System.out.println("While Loop");
        int i = 0;
        while(i<10){
            System.out.println(i + " less than 10");
            i++;
        }
    }
    public static void DoWhileLoop(){
        int i = 0;
        do{
            System.out.println("Hi! " + i + " Times...");
            i++;
        }while(i<10);
    }

}
