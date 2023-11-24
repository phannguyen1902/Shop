package com.shopzay.shopzaybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.shopzay.common.entity","com.shopzay.shopzaybackend.admin.user"})
public class ShopZayBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShopZayBackendApplication.class, args);
    }

}
