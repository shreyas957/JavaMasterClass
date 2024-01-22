package com.shreyas.basics;

import static java.lang.Math.max;   // --> static import.

public class StaticBlockInitialization {

    static {
        System.out.println("static initializer in main");
    }

    public static void main(String[] args) {
        new StaticBlock();
        // System.out.println(Block.count);    // Without even instance of the class the static block is executed.
        System.out.println("--------------------");
        new InstanceBlock();
        System.out.println(InstanceBlock.count);
        System.out.println();
        new InstanceBlock();
        System.out.println(InstanceBlock.count);

        // Static import :
        // System.out.println(Math.max(10, 20)); --> without static import
        System.out.println(max(10, 20));


    }
}

class StaticBlock {
    public static int count;

    // Static block initialization
    static {
        // executed once as soon as class is invoked.
        // We can execute any peace of code inside this static block
        // Cant have return statement and method also there are no arguments here.
        // only static fields can be used inside the code block.
        System.out.println("Static initialization begin");
        count = 0;
        System.out.println("Static initialization end");
    }

    public StaticBlock() {
        System.out.println("Inside default constructor");
    }
}

class InstanceBlock {
    public static int count = 0;

    {
        System.out.println("Instance block initializer");
        count++;
    }

    public InstanceBlock() {
        System.out.println("Inside default constructor");
    }
}
