package com.shreyas.concurrency;

/**
 * This class has the ANSI colour codes for the console output.
 * with the help of these codes, we can change the colour of the text in the console.
 * they work by adding the colour code before the text and then resetting it back to the default colour.
 * @author Shreyas Shevale
 */
public enum ThreadColour {
    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK("\u001B[30m"),
    ANSI_WHITE("\u001B[37m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_RED("\u001B[31m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_YELLOW("\u001B[33m");

    private final String colour;

    ThreadColour(String colour) {
        this.colour = colour;
    }

    public String color() {
        return colour;
    }
}
