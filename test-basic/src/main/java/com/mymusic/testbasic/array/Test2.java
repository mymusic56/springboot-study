package com.mymusic.testbasic.array;

import lombok.Data;

/**
 * @author zhangshangji
 * @since 2021/1/30 16:29
 */
public class Test2 {
    public static void main(String[] args) {
        A a = new A();
        a.setName("李四");
        System.out.println("a.getId() = " + a.getId());
    }
}

@Data
class A {
    private Integer id;
    private String name;
}
