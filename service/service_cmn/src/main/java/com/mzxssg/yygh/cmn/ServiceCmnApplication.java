package com.mzxssg.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-23-19:11
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mzxssg"})
@EnableDiscoveryClient
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }

}
