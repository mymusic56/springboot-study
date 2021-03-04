package com.mymusic.testbasic.enums;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        int[] statusList = new int[11];
        for (int i = 0; i <= 10; i++) {
            statusList[i] = i;
        }
        Map<Integer, String> statusMap = OrderStatusEnum.OrderStatusEnums.map();
        for (int i : statusList) {
            System.out.println(i + " => " + statusMap.getOrDefault(i, "未知"));
        }
    }
}
