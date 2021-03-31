package com.mzxssg.yygh.hosp.service;

import com.mzxssg.yygh.model.hosp.Hospital;
import com.mzxssg.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

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

    //医院列表(条件查询分页）
    Page selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    //更新医院的上线状态
    void updateStatus(String id, Integer status);

    //医院详情信息
    Map<String,Object> getHospById(String id);
}
