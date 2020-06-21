package com.mymusic.testbasic.jdk8;

public class LambdaTest1 {

    static String salutation = "Hello! ";
    public static void main(String[] args) {
        LambdaTest1 test = new LambdaTest1();
        test.dump2();
        test.dump1();
    }

    /**
     * 特点：没有单独去写接口实现类
     * 问题：
     *      如果有多个接口又怎么处理呢？？？？
     */
    private void dump1(){
        GreetingService greetingService = message -> {
            salutation += " & ";
            System.out.println(salutation + message);
        };
        greetingService.sayMessage("zhangsan");
    }

    private void dump2(){
        GreetingService greetingService12 = new GreetingServiceImpl();
        greetingService12.sayMessage("zhangsan");
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    class GreetingServiceImpl implements GreetingService{

        @Override
        public void sayMessage(String message) {
            System.out.println(salutation + message);
        }
    }
}
