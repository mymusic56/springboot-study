package com.mymusic.testbasic.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author zhangshangji
 * @since 2021/2/23 14:53
 */
public class Test {
    public static void main(String[] args) {
        Integer a = 1;
        int b = 2;
        b += a;
        System.out.println("b = " + b);
    }

    private void mathtest(){
        BigDecimal bignum1 = new BigDecimal("10.1");
        BigDecimal bignum2 = new BigDecimal("5.25501");
        BigDecimal bignum3 = null;

        // 加法
        bignum3 = bignum1.add(bignum2).setScale(2, RoundingMode.HALF_UP);
        System.out.println("和 是：" + bignum3);

        // 减法
        bignum3 = bignum1.subtract(bignum2);
        System.out.println("差  是：" + bignum3);

        // 乘法
        bignum3 = bignum1.multiply(bignum2);
        System.out.println("积  是：" + bignum3);

        // 除法
        bignum3 = bignum1.divide(bignum2, 2, RoundingMode.HALF_UP);
        System.out.println("商  是：" + bignum3);
    }
}
