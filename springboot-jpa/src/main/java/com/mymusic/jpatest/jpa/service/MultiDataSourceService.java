package com.mymusic.jpatest.jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Slf4j
public class MultiDataSourceService {

    @Autowired
    @Qualifier("fooDataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("fooJdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public MultiDataSourceService(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getInfo() {

        jdbcTemplate.queryForList("SELECT * FROM FOO")
                .forEach(row -> log.info("------result:"+row.toString()));

        return "multiDataSourceService";
    }
}
