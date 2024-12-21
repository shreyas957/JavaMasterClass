package com.shreyas.generics;

public class GenericClassAndStaticMethod {
    public static void main(String[] args) {
        Temp<Integer> temp = new Temp<>(10);
        System.out.println(temp.getT(20));
        Temp.print("Shreyas");

//        This is used to specify the type to static method
//        Temp.<Integer>print("Shreyas");    -- > throws error
        Temp.<Integer>print(20);
    }

}

class Temp<T> {
    private T t;

    public Temp(T t) {
        this.t = t;
    }

    public T getT(T t) {
        return t;
    }

    // the type T of the static method is different from the type T of the class(including the instance method --> belong to class type T)
    public static <T> void print(T t) {
        System.out.println(t);
    }
}



