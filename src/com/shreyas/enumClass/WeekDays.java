package com.shreyas.enumClass;

public enum WeekDays {
    SUN(1){
        {
            System.out.println("Its holiday");
        }
    },
    MON(2),
    TUE(3),
    WED(4),
    THURS(5),
    FRI(6),
    SAT(7);

    private int id;

    WeekDays(int id) {
        this.id = id;
        System.out.println(this + " " + id);
    }
}
