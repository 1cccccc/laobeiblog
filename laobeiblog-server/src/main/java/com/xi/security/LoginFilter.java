package com.xi.security;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.RSAKey;
import com.xi.common.Result;
import com.xi.common.ReturnEnum;
import com.xi.entity.UserEntity;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {
    private final RSAKey rsaKey = JwtUtils.loadJKSByClassPath();

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RequestMatcher[] notAuthenicationRequests;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!isRequireAuthenication(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        UserEntity userEntity = null;
        try {
            //解析token
            userEntity = JwtUtils.verifyTokenByRSA(token, rsaKey);
        } catch (Exception e) {
            throw new BadCredentialsException("token无效");
        }
        //获取redis中的用户信息
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String s = operations.get("user:" + userEntity.getUserId());
        if (StringUtils.isEmpty(s)) {
            throw new BadCredentialsException("该用户未登录");
        }
        UserEntity entity = JSON.parseObject(s, UserEntity.class);

        //一定要使用三个参数的构造方式，这样才会代表认证通过
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(entity, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    public boolean isRequireAuthenication(HttpServletRequest request) {
        //如果没有需要认证的请求，直接返回true
        if (notAuthenicationRequests == null || notAuthenicationRequests.length == 0) {
            return true;
        }

        for (RequestMatcher requestMatcher : notAuthenicationRequests) {
            //不需要认证的请求需要放行
            if (requestMatcher.matches(request)) {
                return false;
            }
        }

        return true;
    }
}
