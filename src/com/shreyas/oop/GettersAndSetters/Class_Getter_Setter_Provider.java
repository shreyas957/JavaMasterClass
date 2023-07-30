package com.shreyas.oop.GettersAndSetters;

public class Class_Getter_Setter_Provider {
    // Fields : can be static-->(no need of instance) / non-static-->(need instance of class to use).
    // as these field are private we cant use dot notation to access them in other class so,
    // we will use getter and setter to access them individually.
    private String company = "Tesla";
    private String model = "Model X";
    private String color = "Black";
    private int door = 2;
    private boolean convertible = true;
    public String getCompany(){
        // Getter method. Generally use name start with "get".
        return company;
    }
    public void setCompany(String company){
        // Setter method. here String company is passed from other class and
        // this.company is used to specify that "company" with "this" keyword is field
        this.company = company;
    }
    public void setModel(String model){
        // Setter with validation (can we modified as per need this one is for ex)
        if(model == null) model = "Unknown";
        String LowerCaseModel = model.toLowerCase();
        switch (LowerCaseModel){
            case "discovery", "range rover", "sports" -> this.model = model;
            default -> this.model = "Unsupported";
        }
    }
    public void PrintCarInfo(){
        System.out.println(company + ", " + model + ", " + color + ", " + door + " door, " + convertible);
    }


}
