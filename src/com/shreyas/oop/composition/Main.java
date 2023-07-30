package com.shreyas.oop.composition;

public class Main {
    public static void main(String[] args) {
        ComputerCase theCase = new ComputerCase("GHQ2022", "Zephyrus", "160W");
        Monitor theMonitor = new Monitor("14inch HDR", "ZephyrusG14", 14, "1960 x 1080");
        MotherBoard theMotherBoard = new MotherBoard("BJ-200", "Asus", 4, 6, "v2.44");

        PersonalComputer thePC = new PersonalComputer("GHQ2022", "Zephyrus", theCase, theMonitor, theMotherBoard);

        // By following methods we can access the methods on the parts of computer
        // but, we can hide this functionality further by not allowing the call directly to the parts.
        // We don't want anybody to access the Monitor , MotherBoard and ComputerCase directly.
        thePC.getMonitor().drawPixelAt(10, 10, "red");
        thePC.getMotherBoard().loadProgram("IntelliJ IDE");
        thePC.getComputerCase().pressPowerButton();
        System.out.println("********************************");

        thePC.powerUp();

    }
}
