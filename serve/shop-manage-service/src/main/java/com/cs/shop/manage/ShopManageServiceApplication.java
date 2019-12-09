package com.cs.shop.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.cs.shop.manage.mapper")
public class ShopManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopManageServiceApplication.class, args);
    }

}
