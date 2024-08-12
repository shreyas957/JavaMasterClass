package com.shreyas.dates;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Zones {
    public static void main(String[] args) {
//        ZoneId.getAvailableZoneIds();  --> Gives available zones.
        LocalDateTime africa = LocalDateTime.now(ZoneId.of("Africa/Tunis"));
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Afric : " + africa);
        System.out.println("Local : " + now);

        ZonedDateTime nowZone = ZonedDateTime.now();
        System.out.println(nowZone);

    }
}
