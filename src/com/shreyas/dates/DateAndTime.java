package com.shreyas.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class DateAndTime {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.getMonth());
        System.out.println(now.getMinute());
        // and there are many more methods too.

        var now2 = LocalDate.now();
        var now3 = LocalTime.now();

        // Creating specific date.

        LocalDateTime someDate = LocalDateTime.of(
                2023,
                Month.JUNE,
                29,
                12,
                45,
                2
        );
    }
}
