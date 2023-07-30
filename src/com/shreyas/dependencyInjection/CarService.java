package com.shreyas.dependencyInjection;

public class CarService {
//    private CarDao carDao = new CarDao();  --> Without dependency injection.
    private CarDao carDao;

    // Using dependency injection:
    public CarService(CarDao carDao) {  // --> Here the parameter passed is actual dependency passed.
        this.carDao = carDao;
    }
}
