package com.mymusic.testbasic.array;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] a = {1,3,6,10,2,4,5};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        double[] myList = new double[10];
        myList[0] = 5.6;
        myList[1] = 4.5;
        myList[2] = 3.3;
        myList[3] = 13.2;
        myList[4] = 4.0;
        myList[5] = 34.33;
        myList[6] = 34.0;
        myList[7] = 45.45;
        myList[8] = 99.993;
        myList[9] = 11123;
        Arrays.sort(myList);
        System.out.println("myList = " + Arrays.toString(myList));


        sortUsingJava7();
        System.out.println("----------------------------------");
        sortUsingJava8();

    }

    private static void sortUsingJava7(){
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");
        names1.add("Ank ");
        System.out.println("排序前："+names1);

        Collections.sort(names1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("J7升序排列："+names1);
        Collections.sort(names1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("J7降序排列："+names1);
    }

    private static void sortUsingJava8(){
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");
        names1.add("Ank ");
        System.out.println("排序前："+names1);
        Collections.sort(names1, (s1, s2) -> s1.compareTo(s2));
        System.out.println("J8升序排列："+names1);
        Collections.sort(names1, (s1, s2) -> s2.compareTo(s1));
        System.out.println("J8降序排列："+names1);
    }
}
