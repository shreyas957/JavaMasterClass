package com.shreyas.collectionsInJava.enumInCollections;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        enum WeekDays {
            SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
        }

        EnumSet<WeekDays> set = EnumSet.allOf(WeekDays.class);
        System.out.println(set);
        List<WeekDays> list = new ArrayList<>(List.of(WeekDays.SUNDAY, WeekDays.MONDAY, WeekDays.TUESDAY));
        EnumSet<WeekDays> set1 = EnumSet.copyOf(list);
        System.out.println(set1);
        EnumSet<WeekDays> set2 = EnumSet.complementOf(set1);
        System.out.println(set2);

        Map<WeekDays, String> map = new EnumMap<>(WeekDays.class);
        map.put(WeekDays.SUNDAY, "Shreyas");


    }
}
