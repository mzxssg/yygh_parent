package com.mzxssg.yygh.hosp.service;

import com.mzxssg.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-27-20:57
 * @Description:
 */
public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    //根据医院编号查询
    Hospital getByHoscode(String hoscode);
}
