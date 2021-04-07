package com.mzxssg.yygh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mzxssg.yygh.model.user.UserInfo;
import com.mzxssg.yygh.vo.user.LoginVo;
import com.mzxssg.yygh.vo.user.UserAuthVo;
import com.mzxssg.yygh.vo.user.UserInfoQueryVo;

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

    //用户列表（条件查询带分页）
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    //用户锁定
    void lock(Long userId, Integer status);

    //用户详情
    Map<String, Object> show(Long userId);

    //认证审批
    void approval(Long userId, Integer authStatus);
}