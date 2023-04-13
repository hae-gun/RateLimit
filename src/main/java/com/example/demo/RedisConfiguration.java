//package com.example.demo;
//
//import io.lettuce.core.ReadFrom;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.RedisConnectionFailureException;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.convert.Bucket;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableRedisRepositories
//public class RedisConfiguration {
//    private final RedisProperties redisProperties;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .readFrom(ReadFrom.REPLICA_PREFERRED)
//                .build();
//        String host = "192.168.1.188";
//        RedisClusterConfiguration cluster = new RedisClusterConfiguration()
//                .clusterNode(host, 6300)
//                .clusterNode(host, 6301)
//                .clusterNode(host, 6302)
//                .clusterNode(host, 6400)
//                .clusterNode(host, 6401)
//                .clusterNode(host, 6402);
//
//        return new LettuceConnectionFactory(cluster, clientConfig);
//    }
//
//    @Bean
//    RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//
//        return redisTemplate;
//    }
//}
