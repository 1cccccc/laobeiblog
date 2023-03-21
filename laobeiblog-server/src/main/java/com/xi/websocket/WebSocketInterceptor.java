package com.xi.websocket;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class WebSocketInterceptor implements HandshakeInterceptor {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean beforeHandshake(@NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            // 模拟用户（通常利用JWT令牌解析用户信息）
            String userId = servletServerHttpRequest.getServletRequest().getParameter("uid");

            // 判断用户是否存在
//            stringRedisTemplate.opsForValue().get()

            attributes.put("uid", userId);
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request,@NonNull ServerHttpResponse response,@NonNull WebSocketHandler wsHandler,@NonNull Exception exception) {

    }
}
