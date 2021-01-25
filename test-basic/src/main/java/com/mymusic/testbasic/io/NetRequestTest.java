package com.mymusic.testbasic.io;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class NetRequestTest {
    public static void main(String[] args) {

        NetRequestTest test = new NetRequestTest();
        test.getMainData();

        RestTemplate restTemplate = new RestTemplate();
        String url = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("X-APP-KEY", "");
        headers.set("X-Token", "");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("goodsId", "26");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getBody());

        MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();

        stringMultiValueMap.add("早班 9:00-11:00", "周一");
        stringMultiValueMap.add("早班 9:00-11:00", "周二");
        stringMultiValueMap.add("中班 13:00-16:00", "周三");
        stringMultiValueMap.add("早班 9:00-11:00", "周四");
        stringMultiValueMap.add("测试1天2次 09:00 - 12:00", "周五");
        stringMultiValueMap.add("测试1天2次 09:00 - 12:00", "周六");
        stringMultiValueMap.add("中班 13:00-16:00", "周日");
    }

    public void getMainData(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("clientId", "");
        map.add("clientSecret", "");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
            System.out.println("responseEntity.getBody() = " + responseEntity.getBody());

        } catch (HttpClientErrorException e) {
            System.out.println("e.getResponseBodyAsString() = " + e.getResponseBodyAsString());
        }

    }
}
