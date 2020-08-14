package com.mymusic.testbasic.oop.enums;

public enum Gender {
    MAN(1, "男"), WOMAN(1, "女"), OTHER(0, "未知");

    private int code;
    private String desc;

    private Gender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

class Test {
    public static void main(String[] args) {

        for (Gender g : Gender.values()) {
            System.out.println(g.name() + ", g.getCode() = " + g.getCode());
            System.out.println(g.name() + ", g.getDesc() = " + g.getDesc());
        }

        System.out.println(" =========================================");
        System.out.println("Gender.valueOf(Gender.OTHER) = " + Gender.valueOf(Gender.OTHER.name()).getDesc());
        System.out.println(" =========================================");

        System.out.println("Gender.MAN.getCode() = " + Gender.MAN.getCode());
        System.out.println("Gender.MAN.getDesc() = " + Gender.MAN.getDesc());

        System.out.println("Gender.WOMAN.getCode() = " + Gender.WOMAN.getCode());
        System.out.println("Gender.WOMAN.getDesc() = " + Gender.WOMAN.getDesc());

        System.out.println("Gender.OTHER.getCode() = " + Gender.OTHER.getCode());
        System.out.println("Gender.OTHER.getDesc() = " + Gender.OTHER.getDesc());
    }
}
