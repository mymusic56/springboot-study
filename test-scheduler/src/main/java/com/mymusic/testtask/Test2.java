package com.mymusic.testtask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Test2 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 6000)
    public void dumpCurrentDatetime(){
        System.out.println("Test2时间： "+ dateFormat.format(new Date()));
    }
}
