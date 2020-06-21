package com.mymusic.testbasic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class ThreadTest {
    public static void main(String[] args) {
        log.info("程序["+Thread.currentThread()+"]开始执行："+(new Date()));
        Thread t = new Thread(new Task1(), "mythread001");
        t.start();
        Thread t2 = new Thread(new Task2());
        t2.start();
    }
}
