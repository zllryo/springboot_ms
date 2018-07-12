package com.ryo.springboot_ms.redis.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class JedisPoolFactory {
    @Autowired
    private  JedisConfig jedisConfig;

    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(jedisConfig.getMaxIdle());
        poolConfig.setMaxTotal(jedisConfig.getMaxTotal());
        poolConfig.setMaxWaitMillis(jedisConfig.getMaxWait() * 1000);
        JedisPool jp = new JedisPool(poolConfig, jedisConfig.getHost(), jedisConfig.getPort(),
                jedisConfig.getTimeout()*1000);
        return jp;
    }
}
