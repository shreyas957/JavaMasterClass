package com.shreyas.bigdecimal;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal("0.02");
        BigDecimal num2 = new BigDecimal("0.03");
        BigDecimal result = num2.subtract(num1);
        System.out.println(result);

        // Some methods

        BigDecimal number = BigDecimal.TEN;
        System.out.println(number);
        System.out.println(number.add(BigDecimal.ONE));
    }
}
