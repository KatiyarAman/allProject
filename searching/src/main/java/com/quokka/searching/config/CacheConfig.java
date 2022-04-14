package com.quokka.searching.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

@Configuration
@EnableCaching
public class CacheConfig  {

    private static final Logger logger= LoggerFactory.getLogger(CacheConfig.class);

    @Autowired
    private ApplicationConfig applicationConfig;
    //connectionFactory to manage the connection
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(applicationConfig.getRedisServer());
        jedisConnectionFactory.setPort(Integer.parseInt(applicationConfig.getRedisPort()));
        jedisConnectionFactory.setTimeout(Integer.parseInt(applicationConfig.getRedisTimeout()));
        return  jedisConnectionFactory;
    }
    //to comunicate the redish database
    @Bean
    public RedisTemplate<String,String> redisTemplate(){
        RedisTemplate<String,String> redisTemplate= new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
        cacheManager.setDefaultExpiration(Integer.valueOf(applicationConfig.getExperationTime()));
        return cacheManager;
    }
    @Bean
    public Jedis jedisClient() {
        return new Jedis(applicationConfig.getRedisServer(),
                Integer.parseInt(applicationConfig.getRedisPort()));

    }

}
