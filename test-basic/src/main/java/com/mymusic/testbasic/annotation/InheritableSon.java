package com.mymusic.testbasic.annotation;

import java.lang.annotation.*;

/**
 * 自定义的Annotation。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Inheritable {
}

@Inheritable
class InheritableFather
{
    public InheritableFather() {
        // InheritableBase是否具有 Inheritable Annotation
        System.out.println("InheritableFather:"+InheritableFather.class.isAnnotationPresent(Inheritable.class));
    }

    @SuppressWarnings(value = {"eeeee"})
    public void test(){

    }
}

/**
 * InheritableSon 类只是继承于 InheritableFather，
 */
public class InheritableSon extends InheritableFather
{
    public InheritableSon() {
        super();    // 调用父类的构造函数
        // InheritableSon类是否具有 Inheritable Annotation
        //isAnnotationPresent 判断指定的注解是否在当前元素上
        System.out.println("InheritableSon:"+InheritableSon.class.isAnnotationPresent(Inheritable.class));
    }

    public static void main(String[] args)
    {
        InheritableSon is = new InheritableSon();
    }
}
