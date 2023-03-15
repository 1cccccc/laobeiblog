package com.xi.security;


import com.alibaba.fastjson2.JSON;
import com.xi.common.Result;
import com.xi.common.ReturnEnum;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private RequestMatcher[] requestMatchers;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LoginFilter loginFilter) throws Exception {
        //路径匹配
        http.authorizeHttpRequests()
                .requestMatchers(requestMatchers).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;character");
                    response.getWriter().write(JSON.toJSONString(Result.success(ReturnEnum.AUTHENTICATION_FAILD.getCode(), authException.getMessage())));
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setContentType("application/json");
                    response.getWriter().write(JSON.toJSONString(Result.success(ReturnEnum.ACCESSDENIED.getCode(), ReturnEnum.ACCESSDENIED.getMessage())));
                });

        //配置认证过滤器链
        http.addFilterAfter(loginFilter, ExceptionTranslationFilter.class);

        //禁用csrf
        http.csrf().disable();

        //允许跨域
        http.cors();

        //不通过session获取SecurityContext
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
