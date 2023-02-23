package com.xi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan( "com.xi.mapper")
@SpringBootApplication
public class LaobeiblogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaobeiblogServerApplication.class, args);
    }

}
