package com.mymusic.testbasic.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        ScheduleTest scheduleTest = new ScheduleTest();
        //测试每隔10秒执行一次
        scheduleTest.printMsg(10, TimeUnit.SECONDS);
    }

    private void printMsg(long period, TimeUnit unit){
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始执行。");
            }
        }, 0, period, unit);
    }
}
