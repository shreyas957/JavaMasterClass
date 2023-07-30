package com.shreyas.dependencyInjection;

public class Main {
    public static void main(String[] args) {
        //Dependency
        CarDao carDao = new CarDao();

        // Inject
        CarService carService = new CarService(
                //Here CarService need dependency.
                carDao
        );
    }
}
