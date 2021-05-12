package com.mzxssg.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mzxssg.yygh.model.hosp.HospitalSet;
import com.mzxssg.yygh.vo.order.SignInfoVo;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-19-20:56
 * @Description:
 */
public interface HospitalSetService extends IService<HospitalSet> {

    //根据传递过来医院编号，查询数据库，查询签名
    String getSignKey(String hoscode);

    //获取医院签名信息
    SignInfoVo getSignInfoVo(String hoscode);
}
