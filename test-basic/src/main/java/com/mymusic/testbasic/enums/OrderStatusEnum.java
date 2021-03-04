package com.mymusic.testbasic.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public interface OrderStatusEnum {
    @Getter
    enum  OrderStatusEnums {

        WAIT_APY(0, "待支付"),
        PAYED(1, "已付款"),
        SENDED_PAY(9, "已报关"),
        WAIT_SHIP(2, "待发货"),
        SHIP_FAILED(3, "清关失败"),
        WAIT_RECEIVING(4, "待收货"),
        WAIT_COMMENT(5, "待评价"),
        CANCEL(6, "已取消"),
        INVALID(7, "无效"),
        FINISH(8, "已完成");

        private Integer status;
        private String desc;

        OrderStatusEnums(Integer status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public static Map<Integer, String> map(){
            return Arrays.stream(values()).collect(Collectors.toMap(OrderStatusEnums::getStatus,OrderStatusEnums::getDesc));
        }
    }



}
