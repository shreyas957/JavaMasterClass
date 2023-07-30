package com.shreyas.oop.POJOAndRecord;

public class PojoClass {
    // These are POJO fields
    private String StudentName;
    private String StudentID;

    //Constructor
    public PojoClass(String StudentName, String StudentID){
        this.StudentName = StudentName;
        this.StudentID = StudentID;
    }

    @Override
    public String toString() {
        //Special method in java.
        // When instance of the class is passed(here we have Student1, Student2) to System.out.println()
        // then will have toString method implicitly executed.

        return "PojoClass{" +
                "StudentName='" + StudentName + '\'' +
                ", StudentID='" + StudentID + '\'' +
                '}';
    }

    // Getters and Setters
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }
}
