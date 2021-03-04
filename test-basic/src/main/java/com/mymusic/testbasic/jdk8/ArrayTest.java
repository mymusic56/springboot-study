package com.mymusic.testbasic.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("filtered.toString() = " + filtered.toString());

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("squaresList.toString() = " + squaresList.toString());


        List<String> strings2 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", null, "jkl");
        // 获取空字符串的数量
        long count = strings2.stream().filter(string -> string == null || string.isEmpty()).count();
        long count2 = strings2.stream().filter(string -> ArrayTest.isBlank(string)).count();

        System.out.println("count = " + count);
        System.out.println("count2 = " + count2);
    }

    public static boolean isBlank(String str){
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
}
