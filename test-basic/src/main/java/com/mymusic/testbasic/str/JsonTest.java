package com.mymusic.testbasic.str;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String str = "[{\"img\":\"/202011/20201117161723-GUfFfcNcc6.jpeg\", \"id\":\"123\"}," +
                "{\"img\":\"/202011/20201117161723-GUfFfcNcc6.jpeg\", \"id\":\"124\"}]";
        JSONArray jsonArray = JSONObject.parseArray(str);

        for (int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            System.out.println("id = " + jsonObject.get("id") + ", img =" + jsonObject.get("img"));
        }

        List list = JSONObject.parseArray(str);
    }
}
