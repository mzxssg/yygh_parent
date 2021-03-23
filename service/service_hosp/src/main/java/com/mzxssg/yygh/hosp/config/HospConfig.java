package com.mzxssg.yygh.hosp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import jdk.nashorn.internal.ir.ReturnNode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-20-10:35
 * @Description:
 */
@Configuration
@MapperScan("com.mzxssg.yygh.hosp.mapper")
public class HospConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
