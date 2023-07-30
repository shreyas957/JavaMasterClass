package com.shreyas.oop.POJOAndRecord;

public record RecordClass(String StudentName, String StudentID) {
    // I can create my own instance methods and static methods.
    // static fields are allowed to create but cant create non-static fields.

    // compact constructor --> to override canonical constructor
    public RecordClass {
        if(Integer.parseInt(StudentID) < 100){
            throw new IllegalArgumentException("ID should be greater than 100");
        }
    }
}
