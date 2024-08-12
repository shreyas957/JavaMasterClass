package com.shreyas.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;

public class Sample {
    public static void main(String[] args) {
        String str = "apache commons";
        boolean isEmpty = StringUtils.isEmpty(str); // true
        String capitalized = StringUtils.capitalize(str); // "Apache commons"

        System.out.println(isEmpty);
        System.out.println(capitalized);

    }
}
