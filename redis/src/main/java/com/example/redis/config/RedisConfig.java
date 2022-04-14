//package com.example.redis.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import redis.clients.jedis.Jedis;
//
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    ApplicationConfig config;
//
//    @Bean
//    public JedisConnectionFactory factory(){
//        JedisConnectionFactory jedisConnectionFactory= new JedisConnectionFactory();
//        jedisConnectionFactory.setPort(Integer.parseInt(config.getRedisPort()));
//        jedisConnectionFactory.setTimeout(Integer.parseInt(config.getRedisTimeout()));
//        jedisConnectionFactory.setHostName(config.getRedisServer());
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String,String> redisTemplate(){
//        RedisTemplate<String,String> redisTemplate= new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory());
//        return redisTemplate;
//    }
//
//    @Override
//    public KeyGenerator keyGenerator() {
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
//
////    @Bean
////    public KeyGenerator cacheKeyGenerator() {
////        return (target, method, params) -> {
////            StringBuilder sb = new StringBuilder();
////            sb.append(target.getClass().getName());
////            sb.append(method.getName());
////            for (Object obj : params) {
////                sb.append(obj.toString());
////            }
////            return sb.toString();
////        };
////    }
//    @Bean
//    public CacheManager cacheManager(){
//        RedisCacheManager redisCacheManager= new RedisCacheManager(redisTemplate());
//        redisCacheManager.setDefaultExpiration(Integer.parseInt(config.getExperationTime()));
//        return redisCacheManager;
//    }
//    @Bean
//    public Jedis jedisClient() {
//        return new Jedis(config.getRedisServer(),
//                Integer.parseInt(config.getRedisPort()));
//
//    }
//}
