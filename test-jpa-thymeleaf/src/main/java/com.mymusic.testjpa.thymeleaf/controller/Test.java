package com.mymusic.testjpa.thymeleaf.controller;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        long a = (long)1576833561;
        System.out.println((long)1576833561);

        long c = Long.valueOf(String.valueOf(a*1000));
        System.out.println(new Date(c));
    }
}
