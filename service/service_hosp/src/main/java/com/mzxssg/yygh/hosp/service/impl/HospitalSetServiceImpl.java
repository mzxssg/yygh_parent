package com.mzxssg.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzxssg.yygh.common.exception.YyghException;
import com.mzxssg.yygh.common.result.ResultCodeEnum;
import com.mzxssg.yygh.hosp.mapper.HospitalSetMapper;
import com.mzxssg.yygh.hosp.service.HospitalSetService;
import com.mzxssg.yygh.model.hosp.HospitalSet;
import com.mzxssg.yygh.vo.order.SignInfoVo;
import org.springframework.stereotype.Service;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-20-10:09
 * @Description:
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    //根据传递过来医院编号，查询数据库，查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> stringQueryWrapper = new QueryWrapper<>();
        stringQueryWrapper.eq("hoscode", hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(stringQueryWrapper);
        return hospitalSet.getSignKey();
    }

    //获取医院签名信息
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new YyghException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }
}
