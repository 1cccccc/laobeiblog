package com.xi.config.cros;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrosConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//允许所有源
        corsConfiguration.addAllowedHeader("*");//允许所有请求头
        corsConfiguration.addAllowedMethod("*");//允许所有请求方法
        corsConfiguration.setAllowCredentials(true);//允许携带cookie
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);//匹配所有路径

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
