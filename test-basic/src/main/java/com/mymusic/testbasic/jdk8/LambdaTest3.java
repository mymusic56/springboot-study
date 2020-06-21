package com.mymusic.testbasic.jdk8;

public class LambdaTest3 {
    public static void main(String[] args) {
//        int num = 1;
        //局部变量必须用final修饰
        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);
//        num = 5;
    }
    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
