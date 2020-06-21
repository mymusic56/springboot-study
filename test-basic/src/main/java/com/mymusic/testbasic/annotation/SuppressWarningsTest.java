package com.mymusic.testbasic.annotation;

import java.util.Date;

public class SuppressWarningsTest {
    //抑制警告
    @SuppressWarnings(value = {"deprecation"})
    public static void dumpTime(){
        Date date = new Date(13, 8, 26);
        System.out.println(date);
    }
    public static void main(String[] args) {
        dumpTime();
    }
}
