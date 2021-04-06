package com.mzxssg.yygh.hosp.repository;

import com.mzxssg.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-27-20:55
 * @Description:
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    //判断是否存在数据
    Hospital getHospitalByHoscode(String hoscode);

    //根据医院名称查询
    List<Hospital> findHospitalByHosnameLike(String hosname);
}
