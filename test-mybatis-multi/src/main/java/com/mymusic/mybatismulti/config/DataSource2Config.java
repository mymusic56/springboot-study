package com.mymusic.mybatismulti.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.mymusic.mybatismulti.mapper.test2", sqlSessionTemplateRef = "dataSource2SqlSessionTemplate")
public class DataSource2Config {

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }

    @Bean("dataSource2SqlSessionFactory")
    @Qualifier("dataSource2")
    public SqlSessionFactory dataSource2SqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean("dataSource2TransactionManager")
    @Qualifier("dataSource2")
    public DataSourceTransactionManager dataSource2TransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("dataSource2SqlSessionTemplate")
    @Qualifier("dataSource2SqlSessionFactory")
    public SqlSessionTemplate dataSource2SqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
