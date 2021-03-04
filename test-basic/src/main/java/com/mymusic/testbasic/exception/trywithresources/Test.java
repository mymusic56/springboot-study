package com.mymusic.testbasic.exception.trywithresources;

/**
 * try-with-resources
 * @author zhangshangji
 * @since 2021/2/9 13:45
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.autoCloseResource();
    }

    public void autoCloseResource() {
        try (MyRedis myRedis = new MyRedis(); MyRedis myRedis2 = new MyRedis();) {
            String value = myRedis.get("zhangSan");
            System.out.println("value = " + value);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        try (MyRedis myRedis = new MyRedis()) {
            String value = myRedis.get("liSi");
            System.out.println("value = " + value);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        } finally {
            System.out.println("finally = " + true);
        }
    }
}

class MyRedis implements AutoCloseable {

    public String get(String key) {
        return key;
    }

    @Override
    public void close() throws Exception {
        System.out.println("AutoCloseable[自动关闭资源]");
//        throw new Exception("自动关闭资源异常！");
    }
}
