package com.mymusic.testbasic.util;

import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date(1506725913000L);
        System.out.println("date = " + date);
        date.getTime();
    }
}
