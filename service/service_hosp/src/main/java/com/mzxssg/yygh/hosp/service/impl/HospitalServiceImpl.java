package com.mzxssg.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mzxssg.yygh.hosp.repository.HospitalRepository;
import com.mzxssg.yygh.hosp.service.HospitalService;
import com.mzxssg.yygh.hosp.service.HospitalSetService;
import com.mzxssg.yygh.model.hosp.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-27-20:58
 * @Description:
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    //上传医院接口
    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数map集合转换对象
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);
        //判断是否存在数据
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);
        //如果存在，进行修改
        if (hospitalExist!=null){
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(hospitalExist.getUpdateTime());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else {
            //如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    //根据医院编号查询
    @Override
    public Hospital getByHoscode(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        return hospital;
    }
}
