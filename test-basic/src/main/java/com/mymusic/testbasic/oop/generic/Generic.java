package com.mymusic.testbasic.oop.generic;

public class Generic<T> {
    //在类中声明的泛型整个类里面都可以用，除了静态部分，因为泛型是实例化时声明的。
    //静态区域的代码在编译时就已经确定，只与类相关
    class A <E>{
        T t;
    }
    //类里面的方法或类中再次声明同名泛型是允许的，并且该泛型会覆盖掉父类的同名泛型T
    class B <T>{
        T t;
    }
    //静态内部类也可以使用泛型，实例化时赋予泛型实际类型
    static class C <T> {
        T t;
    }
    public static void main(String[] args) {
        //报错，不能使用T泛型，因为泛型T属于实例不属于类
        Generic<Integer> g1 = new Generic<>(1);
        System.out.println("g1.getKey() = " + g1.getKey());

        Generic<Double> g2 = new Generic<>(1.3);
        System.out.println("g2.getKey() = " + g2.getKey());
    }

    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}
