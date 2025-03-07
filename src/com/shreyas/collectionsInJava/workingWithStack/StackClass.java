package com.shreyas.collectionsInJava.workingWithStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class StackClass {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        System.out.println(stack.size());
        System.out.println(stack.push(5));
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.empty());

        Deque<Integer> stack2 = new ArrayDeque<>();   // stack implementation using ArrayDeque
    }
}
