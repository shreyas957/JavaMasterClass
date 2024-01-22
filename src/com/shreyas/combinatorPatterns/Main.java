package com.shreyas.combinatorPatterns;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(
                "Shreyas",
                "shreyas@gmail.com",
                LocalDate.of(2003, 6, 29)
        );

       ValidationResult result = CustomerValidateCombinatorPattern.isEmailValid()
                .and(CustomerValidateCombinatorPattern.isAdult()).apply(customer);

        System.out.println(result);

    }
}