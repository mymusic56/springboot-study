package com.mymusic.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
@Slf4j
public class RedisAdapter {
    @Autowired
    private JedisPool jedisPool;

    public String set(String key, String value, int expireTime){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            return jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} expireTime:{} error", key, value, expireTime, e);
        } finally {
            close(jedis);
        }
        return  null;
    }
    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
