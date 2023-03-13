package com.xi.security;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;

@Configuration
public class CustomConfig {
    @Resource
    private IgnoreRequest ignoreRequest;

    @Bean
    public RequestMatcher[] requestMatchers(){
        ArrayList<RequestMatcher> requestMatcherArrayList = new ArrayList<>();
        ignoreRequest.getRequests().forEach(path->requestMatcherArrayList.add(new AntPathRequestMatcher(path)));

        return requestMatcherArrayList.toArray(new RequestMatcher[0]);
    }
}
