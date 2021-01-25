package com.mymusic.testbasic.oop;

public class Test {
    public static void main(String[] args) {
        Ab ab = new Ab();
        //空指针
        System.out.println("ab.getB().isEmpty() = " + ab.getB().isEmpty());
        ab.setB("");

        System.out.println("ab.getB().isEmpty() = " + ab.getB().isEmpty());
        ab.setB(null);
        //空指针
        System.out.println("ab.getB().isEmpty() = " + ab.getB().isEmpty());
    }
}

class Ab {
    private int a;
    private String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}