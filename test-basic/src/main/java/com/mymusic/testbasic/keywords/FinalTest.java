package com.mymusic.testbasic.keywords;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * final、finally
 * 防御性拷贝
 * @author zhangshangji
 * @since 2021/2/9 10:31
 */
public class FinalTest {
    final String name = "张三";
    String firstName = "张";


    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        finalTest.testObject();

        finalTest.firstName = "李";

        finalTest.test1();

    }

    /**
     * 防御性拷贝的原因就是因为，final只能约束引用赋值，无法约束对象
     */
    public void test1() {
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        long endTime = end.getTime();
        System.out.println("period.getEnd() = " + period.getEnd());
        end.setTime(endTime + 86400 * 1000);
        System.out.println("period.getEnd() = " + period.getEnd());
        period.getEnd().setTime(endTime);

        //防御性拷贝：对输入的对象修改无效
        Period period2 = new Period(start, end, true);

        System.out.println("before = " + period2.getEnd());
        end.setTime(endTime + 86400 * 1000);
        System.out.println("after = " + period2.getEnd());
        period2.getEnd().setTime(endTime);

        //此时仍可以对获取的对象的属性进行修改
        period2.getEnd().setTime(endTime + 86400 * 1000);
        System.out.println("after = " + period2.getEnd());
        period2.getEnd().setTime(endTime);

        //此时通过Get方法获取的是一个新的对象
        period2.getEnd(true).setTime(endTime + 86400 * 1000);
        System.out.println("after = " + period2.getEnd());
        period2.getEnd().setTime(endTime);
    }


    public void testObject() {
        final List<String> strList = new ArrayList<>();
        strList.add("zhangsan");
        strList.add("list");
        System.out.println("strList.toString() = " + strList.toString());
        //final只能约束引用赋值
    }

    final class FinalClassTest {
        public void printString() {
            System.out.println("print = " + 1234);
        }
    }

    final class Period {
        private final Date start;
        private final Date end;

        public Period(Date start, Date end) {
            if (start.compareTo(end) > 0) throw new IllegalArgumentException(start + " after " + end);
            this.start = start;
            this.end = end;
        }

        public Period(Date start, Date end, boolean defensiveCopy) {
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());
            if (this.start.compareTo(this.end) > 0)
                throw new IllegalArgumentException(this.start + " after " + this.end);
        }

        public Date getStart() {
            return start;
        }

        public Date getStart(boolean defensive) {
            return new Date(this.start.getTime());
        }

        public Date getEnd() {
            return end;
        }

        public Date getEnd(boolean defensive) {
            return new Date(this.end.getTime());
        }
    }
}
