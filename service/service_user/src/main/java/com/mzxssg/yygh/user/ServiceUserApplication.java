package com.mzxssg.yygh.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Zexin Ma
 * @Create: 2021-04-02-20:37
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.mzxssg")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mzxssg")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}