package com.mymusic.testbasic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class Task1 implements Runnable{

    @Override
    public void run() {
        while (true) {
            log.info("Task1:"+Thread.currentThread().getName()+"任务开始执行："+(new Date()));
            try {
                Thread.sleep(9000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
