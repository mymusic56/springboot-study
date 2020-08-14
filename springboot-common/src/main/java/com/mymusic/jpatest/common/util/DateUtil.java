package com.mymusic.jpatest.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static int getTimestamp(){
        return Integer.valueOf(String.valueOf(new Date().getTime()/1000));
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(calendar.getTime());
    }
}
