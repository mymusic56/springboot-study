package com.mymusic.testbasic.var;

public class Test {
    private Integer a = 21;
    public static void main(String[] args) {
        Test test = new Test();
        test.a += 2147483647;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(test.a);
    }
}
