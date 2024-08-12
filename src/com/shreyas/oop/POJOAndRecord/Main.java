package com.shreyas.oop.POJOAndRecord;

public class Main {
    public static void main(String[] args) {
        //POJO:
        PojoClass POJOStudent1 = new PojoClass("Shreyas", "111");
        PojoClass POJOStudent2 = new PojoClass("Shreeya", "112");
        System.out.println(POJOStudent1);
        System.out.println(POJOStudent2);
        System.out.println(POJOStudent1.getStudentName());
        System.out.println(POJOStudent2.getStudentName());

        //Record Class
        RecordClass recordStudent1 = new RecordClass("Shreyas", "112");
        System.out.println(recordStudent1);
        System.out.println(recordStudent1.StudentName());

        RecordClass recordStudent2 = new RecordClass("Shreyas", "10");
        System.out.println(recordStudent2);
        System.out.println(recordStudent2.StudentName());

    }
}
