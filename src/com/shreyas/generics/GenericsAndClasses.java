package com.shreyas.generics;

class Box<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Box{" +
                "t=" + t +
                '}';
    }
}

class Phone {
    private String number;

    public Phone(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                '}';
    }
}

public class GenericsAndClasses {
    public static void main(String[] args) {
        Box<Phone> phoneBox = new Box<>();
        phoneBox.setT(new Phone("9657796022"));
        System.out.println(phoneBox.getT());

    }
}
