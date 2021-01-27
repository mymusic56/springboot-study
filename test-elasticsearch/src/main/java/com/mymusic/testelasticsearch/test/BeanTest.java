package com.mymusic.testelasticsearch.test;

import com.alibaba.fastjson.JSONObject;
import com.mymusic.testelasticsearch.bean.DbSubscribeBean;

public class BeanTest {
    public static void main(String[] args) {
        String str = "{\"before\":{},\"eventType\":\"UPDATE\",\"after\":{\"user_desc\":\"desc\",\"password\":\"zhangsanlisi\",\"updated_at\":\"0\",\"role_id\":\"0\",\"name\":\"8\",\"created_at\":\"0\",\"id\":\"8\",\"email\":\"1\",\"age\":\"86\"},\"db\":\"test\",\"table\":\"users\"}";
        DbSubscribeBean bean = JSONObject.parseObject(str, DbSubscribeBean.class);
        Object object = bean.getBefore();
        System.out.println("bean.getAfter() = " + bean.getBefore());
    }
}
