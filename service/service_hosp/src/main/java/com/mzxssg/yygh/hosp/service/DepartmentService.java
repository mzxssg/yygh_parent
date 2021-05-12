package com.mzxssg.yygh.hosp.service;

import com.mzxssg.yygh.model.hosp.Department;
import com.mzxssg.yygh.vo.hosp.DepartmentQueryVo;
import com.mzxssg.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-28-19:12
 * @Description:
 */
public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);

    //查询科室接口
    Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);

    //删除科室接口
    void remove(String hoscode, String depcode);

    //根据医院编号，查询医院所有科室列表
    List<DepartmentVo> findDeptTree(String hoscode);

    //获取科室名称
    String getDepName(String hoscode, String depcode);

    //根据科室编号，和医院编号，查询科室
    Department getDepartment(String hoscode, String depcode);
}
