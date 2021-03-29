package com.mzxssg.yygh.hosp.repository;

import com.mzxssg.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-28-19:10
 * @Description:
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    //上传科室接口
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
