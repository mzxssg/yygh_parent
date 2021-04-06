package com.mzxssg.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mzxssg.yygh.model.user.UserInfo;
import com.mzxssg.yygh.vo.user.LoginVo;
import com.mzxssg.yygh.vo.user.UserAuthVo;

import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-04-02-20:53
 * @Description:
 */
public interface UserInfoService extends IService<UserInfo> {

    //用户手机号登陆接口
    Map<String, Object> loginUser(LoginVo loginVo);

    //根据openid进行数据库查询
    UserInfo getByOpenid(String openId);

    //用户认证接口
    void userAuth(Long userId, UserAuthVo userAuthVo);
}