package com.luo.diamonds;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan("com.luo.diamonds.mapper")
@ServletComponentScan
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class DiamondsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiamondsApplication.class, args);
    }

}
