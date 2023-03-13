package com.xi.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
 @Bean
 public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
  RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
  FastJsonRedisSerializer fastjsonRedisSerializer = new FastJsonRedisSerializer(Object.class);

  //value序列化方式
  redisTemplate.setValueSerializer(fastjsonRedisSerializer);
  redisTemplate.setHashValueSerializer(fastjsonRedisSerializer);

  //key序列化方式
  redisTemplate.setKeySerializer(new StringRedisSerializer());
  redisTemplate.setHashKeySerializer(new StringRedisSerializer());
  redisTemplate.setConnectionFactory(redisConnectionFactory);//设置连接工厂


  return redisTemplate;
 }
}
