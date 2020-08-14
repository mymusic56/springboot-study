package com.mymusic.testbasic.oop.clazz;

public class StaticClassTest {

    private static int type = 0;

    private int age = 11;

    public class InnerClass {
        int b = 30;
        /**
         * 访问外部类的属性或方法
         * @return
         */
        public int getAge(){
            return age;
        }
    }

    private class InnerPrivateClass{
        int c = 10;
    }

    public static class InnerStaticClass {
        private int a;

        private static InnerStaticClass innerStaticClass = new InnerStaticClass();

        public static InnerStaticClass getInstance() {
            return InnerStaticClass.innerStaticClass;
        }
        /**
         * 访问外部类的属性或方法
         * @return
         */
        public int getAge(){
            StaticClassTest test = new StaticClassTest();
            return test.age;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}

class Test {
    public static void main(String[] args) {
        StaticClassTest test = new StaticClassTest();
        StaticClassTest.InnerClass innerClass = test.new InnerClass();
        int b = innerClass.b;
//        int b = new StaticClassTest().new InnerClass().b;
        System.out.println("b = " + b);
        System.out.println("访问外部类 age = " + innerClass.getAge());

        //内部类为私有的情况会报错
//        StaticClassTest.InnerPrivateClass innerClass2 = test.new InnerPrivateClass();
//        int c = innerClass2.c;
//        System.out.println("c = " + c);


        //静态内部类
        StaticClassTest.InnerStaticClass innerStaticClass = StaticClassTest.InnerStaticClass.getInstance();
        innerStaticClass.setA(31111);
        System.out.println("innerStaticClass.getA() = " + innerStaticClass.getA());
        int a = innerStaticClass.getAge();
        System.out.println("a = " + a);

        StaticClassTest.InnerStaticClass innerStaticClass2 = StaticClassTest.InnerStaticClass.getInstance();
        System.out.println("innerStaticClass2.getA() = " + innerStaticClass2.getA());


    }
}