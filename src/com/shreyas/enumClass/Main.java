package com.shreyas.enumClass;

import java.util.Random;

public class Main {

    enum Gender {
        MALE,
        FEMALE
    }
    public static void main(String[] args) {
        Days day1 = Days.MON;
        System.out.println(day1);
        System.out.println("Name : " + day1.name() + ", Ordinal value : " + day1.ordinal());
        day1 = getRandomDay();
        System.out.println(day1);

    }

    public static Days getRandomDay(){
        int randomNum = new Random().nextInt(7);
        var day = Days.values();
        return day[randomNum];
    }
}


// Switch case can have enum type as variable.