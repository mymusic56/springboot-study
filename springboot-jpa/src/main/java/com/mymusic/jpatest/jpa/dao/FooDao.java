package com.mymusic.jpatest.jpa.dao;

import com.mymusic.jpatest.jpa.dto.FooDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class FooDao {

    @Autowired
    @Qualifier("db1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll(){
        return jdbcTemplate.queryForList("select * from foo where status =1");
    }

    public FooDto findById(int id, int status){
        //queryForObject不能直接映射实体类
//        jdbcTemplate.queryForObject("select COUNT(*) from foo", FooDto.class);
        List<FooDto> d = jdbcTemplate.query("select * from foo where id =? AND status=?", new Object[]{id,status}, new RowMapper<FooDto>(){
            @Override
            public FooDto mapRow(ResultSet resultSet, int i) throws SQLException {
                FooDto foo = new FooDto();
                foo.setId(resultSet.getLong("id"));
                foo.setFoo(resultSet.getString("foo"));
                return foo;
            }
        });

        if (!d.isEmpty()) {
            return d.get(0);
        }
        return null;
    }

    public int count(int status){
        return jdbcTemplate.queryForObject("select COUNT(*) from foo where status=?", Integer.class, status);
    }

    public Map<String, String> insertData(Map<String, String> params){
        HashMap<String, String> row = new HashMap<>();
        row.put("foo", params.get("foo"));
        row.put("status", params.get("status"));
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

//        log.info("row: "+row.toString());
        //返回1
        Number id = simpleJdbcInsert
                .withTableName("foo")
                .usingColumns("status","foo")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(row);

        if (id.longValue() > 0) {
            row.put("id", String.valueOf(id.longValue()));
        }

        return row;
    }
}
