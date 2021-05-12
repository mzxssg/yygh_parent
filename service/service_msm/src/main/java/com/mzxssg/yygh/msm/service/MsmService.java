package com.mzxssg.yygh.msm.service;

import com.mzxssg.yygh.vo.msm.MsmVo;

/**
 * @Author: Zexin Ma
 * @Create: 2021-04-03-13:12
 * @Description:
 */
public interface MsmService {

    //发送手机验证码
    boolean send(String phone, String code);

    //mq使用发送短信
    boolean send(MsmVo msmVo);
}