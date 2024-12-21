package com.shreyas.dates;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class Zones {
    public static void main(String[] args) {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();// --> Gives available zones.
        System.out.println(availableZoneIds);
        LocalDateTime africa = LocalDateTime.now(ZoneId.of("Africa/Tunis"));
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Afric : " + africa);
        System.out.println("Local : " + now);

        ZonedDateTime nowZone = ZonedDateTime.now();
        System.out.println(nowZone);

    }
}
