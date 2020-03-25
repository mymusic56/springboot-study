package com.mymusic.jpatest.jpa.controller;

import com.mymusic.jpatest.jpa.component.SpringContextUtil;
import com.mymusic.jpatest.jpa.service.MultiDataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/multiDataSource")
@Slf4j
public class MultiDataSourceController {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MultiDataSourceService multiDataSourceService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Autowired
    public MultiDataSourceController(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        //在控制器层使用使用变量注入失败，故构造器注入
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("getInfo")
    public String getInfo() {
        dataSource = (DataSource) SpringContextUtil.getBean("db1DataSource");
        jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean("db1JdbcTemplate");

        MultiDataSourceController mds = new MultiDataSourceController(dataSource, jdbcTemplate);
        try {
            boolean flag = mds.showConnection();
            if (flag) {
                mds.showData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("getService")
    public String getService() {
        return multiDataSourceService.getInfo();
    }

    private boolean showConnection() throws SQLException {
        log.info("------data_source:"+dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info("------conn:"+conn.toString());
        conn.close();
        return true;
    }

    private void showData() {
        jdbcTemplate.queryForList("SELECT * FROM users")
                .forEach(row -> log.info("------result:"+row.toString()));
    }
}
