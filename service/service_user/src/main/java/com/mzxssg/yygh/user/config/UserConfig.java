package com.mzxssg.yygh.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zexin Ma
 * @Create: 2021-04-03-9:25
 * @Description:
 */
@Configuration
@MapperScan("com.mzxssg.yygh.user.mapper")
public class UserConfig {
}
