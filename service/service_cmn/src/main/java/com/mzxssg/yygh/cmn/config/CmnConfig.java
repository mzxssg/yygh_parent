package com.mzxssg.yygh.cmn.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-20-10:35
 * @Description:
 */
@Configuration
@MapperScan("com.mzxssg.yygh.cmn.mapper")
public class CmnConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
