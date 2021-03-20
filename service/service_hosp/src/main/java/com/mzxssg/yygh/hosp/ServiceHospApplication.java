package com.mzxssg.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-19-19:25
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.mzxssg")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}