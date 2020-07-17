package com.mymusic.jpatest.common.util;

import org.apache.tomcat.util.security.MD5Encoder;

public class PasswordUtil {
    public static String getEncryptPassword(String salt, String password){
        return MD5Encoder.encode((password+salt).getBytes());
    }
}
