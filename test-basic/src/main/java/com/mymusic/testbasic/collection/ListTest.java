package com.mymusic.testbasic.collection;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhangshangji
 * @since 2021/2/25 10:00
 */
public class ListTest {
    public static void main(String[] args) {
        ListTest test = new ListTest();
        test.test1();
    }

    private void test(){
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("zhangsan2");
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        Arrays.stream(arr).forEach(item -> System.out.println(item));
        System.out.println("===========");

        List<String> list2 = Arrays.asList(arr);
        // list2.add("wangwu");//不能进行add/remove/clear操作， UnsupportedOperationException
        list2.forEach(item -> System.out.println("item = " + item));
        System.out.println("===========");
        arr[1] = "22222";
        list2.forEach(item -> System.out.println("item = " + item));

        System.out.println("=====iterator======");
        Iterator<String> iterator = list2.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }
    }

    private void test1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (String item : list) {
            System.out.println("item = " + item);
            if ("3".equals(item)) {
                System.out.println("delete item = " + item);
                list.remove(item);
            }
        }

        System.out.println(JSONObject.toJSON(list));
    }
}
