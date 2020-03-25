package com.mymusic.jpatest.jpa.rowMaper;

import com.mymusic.jpatest.jpa.dto.BarDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BarRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return BarDto.builder()
                .id(resultSet.getLong("id"))
                .bar(resultSet.getString("bar"))
                .build();
    }
}
