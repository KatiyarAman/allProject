package com.example.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

@Configuration
@EnableCaching
public class StringRedish  {

    @Autowired
    ApplicationConfig config;

    @Bean
    public JedisConnectionFactory factory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(Integer.parseInt(config.getRedisPort()));
        jedisConnectionFactory.setTimeout(Integer.parseInt(config.getRedisTimeout()));
        jedisConnectionFactory.setHostName(config.getRedisServer());
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate redisTemplateJedis() {
        StringRedisTemplate redisTemplateJedis = new StringRedisTemplate();
        redisTemplateJedis.setConnectionFactory(factory());
        redisTemplateJedis.setKeySerializer(new StringRedisSerializer());
        redisTemplateJedis.setValueSerializer(new StringRedisSerializer());
        redisTemplateJedis.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplateJedis.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplateJedis;
    }
        @Bean
        public CacheManager cacheManager () {
            RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplateJedis());
            redisCacheManager.setDefaultExpiration(Integer.parseInt(config.getExperationTime()));
            return redisCacheManager;
        }
        @Bean
        public Jedis jedisClient () {
            return new Jedis(config.getRedisServer(),
                    Integer.parseInt(config.getRedisPort()));

        }
//    @Bean
//    public KeyGenerator cacheKeyGenerator() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(method.getName());
//            for (Object obj : params) {
//                sb.append(obj.toString());
//            }
//            return sb.toString();
//        };
//    }
    }

