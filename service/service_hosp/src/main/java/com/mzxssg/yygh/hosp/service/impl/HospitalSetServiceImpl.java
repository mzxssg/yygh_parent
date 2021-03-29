package com.mzxssg.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzxssg.yygh.hosp.mapper.HospitalSetMapper;
import com.mzxssg.yygh.hosp.service.HospitalSetService;
import com.mzxssg.yygh.model.hosp.HospitalSet;
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
}
