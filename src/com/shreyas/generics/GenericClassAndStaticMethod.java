package com.shreyas.generics;

public class GenericClassAndStaticMethod<T> {
    private T t;

    public GenericClassAndStaticMethod(T t) {
        this.t = t;
    }

    public T getT(T t) {
        return t;
    }

    public static <T> void print(T t) {     // This T is different from the T in the class
        System.out.println(t);
    }

}



