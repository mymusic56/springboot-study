package com.mymusic.testelasticsearch.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DbSubscribeBean {

    private String eventType;
    private String db;
    private String table;
    private String before;
    private String after;
}
