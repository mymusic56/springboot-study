package com.mymusic.jpatest.common.util;

import java.util.Date;

public class DateUtil {
    public static int getTimestamp(){
        return Integer.valueOf(String.valueOf(new Date().getTime()/1000));
    }
}
