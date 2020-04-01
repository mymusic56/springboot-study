package com.mymusic.testtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Test1 {

    private int count;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Scheduled(cron = "*/10 * * * * ?")
    public void process(){
        try {
            System.out.println("Test1----： "+ 1);
            Thread.sleep(3000);
            System.out.println("Test1----： "+ 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test1时间： "+ dateFormat.format(new Date()));

    }

}
