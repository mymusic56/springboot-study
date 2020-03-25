package com.mymusic.jpatest.jpa.dao;

import com.mymusic.jpatest.jpa.rowMaper.BarRowMapper;
import com.mymusic.jpatest.jpa.dto.BarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BarDao {

    @Autowired
    @Qualifier("db1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public BarDto findById(int id) {
        String sql = "select * from bar where id=? limit 1";
        List<BarDto> barList = jdbcTemplate.query(sql, new Object[]{id}, new BarRowMapper());

        if (!barList.isEmpty()) {
            return barList.get(0);
        }
        return null;
    }

}
