package com.mymusic.jpatest.jpa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置多数据源
 */
@Configuration
@Slf4j
@PropertySource("classpath:multi-datasource.properties")
public class MultiDataSourceConfig {

    //配置内存数据库
    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource() {
        DataSourceProperties dsp = fooDataSourceProperties();
        log.info("-----foo.datasource url: "+dsp.getUrl());
        return dsp.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public JdbcTemplate fooJdbcTemplate(){
        return new JdbcTemplate(fooDataSource());
    }

    @Bean
    @Primary
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource barDataSource() {
        DataSourceProperties dsp = barDataSourceProperties();
        log.info("-----bar.datasource url: "+dsp.getUrl());
        return dsp.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    @Primary
    public JdbcTemplate barJdbcTemplate(){
        return new JdbcTemplate(barDataSource());
    }


    //配置MySQL数据库
    @Bean
    @ConfigurationProperties("db1.mysql.datasource")
    public DataSourceProperties db1DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db1DataSource() {
        DataSourceProperties dsp = db1DataSourceProperties();
        log.info("-----db1.mysql.datasource url: "+dsp.getUrl());
        return dsp.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public JdbcTemplate db1JdbcTemplate(){
        return new JdbcTemplate(db1DataSource());
    }

    @Bean
    @ConfigurationProperties("db2.mysql.datasource")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db2DataSource() {
        DataSourceProperties dsp = db2DataSourceProperties();
        log.info("-----db2.mysql.datasource url: "+dsp.getUrl());
        return dsp.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public JdbcTemplate db2JdbcTemplate(){
        return new JdbcTemplate(db2DataSource());
    }

//    //自定义事务管理器
//    @Bean
//    @Resource
//    public ResourceTransactionManager txManager(DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }


    //数据初始化
    @Bean
    public DataSourceInitializer fooDataSourceInitializer(@Qualifier("fooDataSource") DataSource dataSource) {
        System.out.println("----------------初始化内置数据库[foo]----------------------");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("sql/foo-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("sql/foo-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }


    @Bean
    public DataSourceInitializer barDataSourceInitializer(@Qualifier("barDataSource") DataSource dataSource) {
        System.out.println("----------------初始化内置数据库[bar]----------------------");

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("sql/bar-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("sql/bar-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

   /* @Bean
    public DataSourceInitializer db1DataSourceInitializer(@Qualifier("db1DataSource") DataSource dataSource) {
        System.out.println("----------------初始化内置数据库[db1]----------------------");

        //检测是否已初始化

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("sql/bar-schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("sql/bar-data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }*/

}
