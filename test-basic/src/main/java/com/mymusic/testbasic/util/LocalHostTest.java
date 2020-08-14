package com.mymusic.testbasic.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHostTest {
    public static void main(String[] args) {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            if (hostname == null || hostname.isEmpty()) {
                throw new UnknownHostException();
            }
            hostname = "www.baidu.com";
            System.out.println("hostname = " + hostname);
            String[] tokens = hostname.split("\\.");
            System.out.println("token = " + tokens[tokens.length - 1]);
            System.out.println();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
