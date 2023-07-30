package com.shreyas.oop.composition;

public class PersonalComputer extends Product{
    // Here we have 3 fields that are consist of 3 different classes
    private ComputerCase computerCase;
    private Monitor monitor;
    private MotherBoard motherBoard;

    public PersonalComputer(String model, String manufacturer, ComputerCase computerCase, Monitor monitor, MotherBoard motherBoard) {
        super(model, manufacturer);
        this.computerCase = computerCase;
        this.monitor = monitor;
        this.motherBoard = motherBoard;
    }

    public ComputerCase getComputerCase() {
        return computerCase;
    }
    public Monitor getMonitor() {
        return monitor;
    }
    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    private void drawLogo(){
        monitor.drawPixelAt(11, 11, "blue");
    }
    public void powerUp(){
        computerCase.pressPowerButton();
        drawLogo();
    }
}
