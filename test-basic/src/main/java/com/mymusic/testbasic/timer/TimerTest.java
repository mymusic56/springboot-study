package com.mymusic.testbasic.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest extends TimerTask {
    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(calendar.getTime()));
    }

    public static void main(String[] args) {
        TimerTest myTimerTest = new TimerTest();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(myTimerTest, 0, 5000);
        System.out.println("最后一行代码被执行");
    }
}
