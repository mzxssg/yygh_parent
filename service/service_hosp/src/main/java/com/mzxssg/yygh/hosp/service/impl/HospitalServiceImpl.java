package com.mzxssg.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mzxssg.yygh.cmn.client.DictFeignClient;
import com.mzxssg.yygh.hosp.repository.HospitalRepository;
import com.mzxssg.yygh.hosp.service.HospitalService;
import com.mzxssg.yygh.hosp.service.HospitalSetService;
import com.mzxssg.yygh.model.hosp.Hospital;
import com.mzxssg.yygh.vo.hosp.HospitalQueryVo;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-27-20:58
 * @Description:
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DictFeignClient dictFeignClient;

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

    //医院列表(条件查询分页）
    @Override
    public Page selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        //创建pageable对象
        Pageable pageable = PageRequest.of(page-1, limit);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                            .withIgnoreCase();

        //hospitalQueryVo转换Hospital对象
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);

        //创建对象
        Example<Hospital> example = Example.of(hospital, exampleMatcher);
        //调用方法实现查询
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);

        //获取查询list集合，遍历进行医院等级封装
        pages.getContent().stream().forEach(item->{
            this.setHospitalHosType(item);
        });

        return pages;
    }

    //更新医院的上线状态
    @Override
    public void updateStatus(String id, Integer status) {
        //根据id查询医院信息
        Hospital hospital = hospitalRepository.findById(id).get();
        //设置修改的值
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        hospitalRepository.save(hospital);
    }

    //医院详情信息
    @Override
    public Map<String,Object> getHospById(String id) {
        Map<String,Object> result = new HashMap<>();
        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());
        //医院基本信息（包含医院等级）
        result.put("hospital", hospital);
        //预约信息
        result.put("bookingRule", hospital.getBookingRule());
        return result;
    }

    @Override
    public String getHospName(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        if(hospital!=null){
            return hospital.getHosname();
        }
        return null;
    }

    //根据医院名称查询
    @Override
    public List<Hospital> findByHosname(String hosname) {
        return hospitalRepository.findHospitalByHosnameLike(hosname);
    }

    //根据医院编号获取医院预约挂号详情
    @Override
    public Map<String, Object> item(String hoscode) {
        Map<String, Object> result = new HashMap<>();
        //医院详情
        Hospital hospital = this.setHospitalHosType(this.getByHoscode(hoscode));
        result.put("hospital", hospital);
        //预约规则
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }

    //获取查询list集合，遍历进行医院等级封装
    private Hospital setHospitalHosType(Hospital hospital) {
        //根据dictCode和value获取医院等级名称
        String hostype = dictFeignClient.getName("Hostype",hospital.getHostype());
        //查询省 市 地区
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("fullAddress", provinceString+cityString+districtString);
        hospital.getParam().put("hostype", hostype);
        return hospital;
    }
}
