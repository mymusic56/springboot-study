package com.mymusic.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class CanalClient {

    @Value("${spring.canal.hostname}")
    private String hostname;

    @Value("${spring.canal.port}")
    private Integer prot;

    @Value("${spring.canal.destination}")
    private String destination;

    @Value("${spring.canal.username}")
    private String username;

    @Value("${spring.canal.password}")
    private String password;

    @Value("${spring.canal.filter}")
    private String filter;


    @Bean
    public CanalConnector createConnector() {
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(hostname, prot),
                destination, username, password);
        if (filter == null) {
            filter = ".*\\..*";
        }
        connector.connect();
        connector.subscribe(filter);
        connector.rollback();
        return connector;
    }
}
