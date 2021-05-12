package com.mzxssg.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mzxssg.yygh.hosp.repository.DepartmentRepository;
import com.mzxssg.yygh.hosp.service.DepartmentService;
import com.mzxssg.yygh.model.hosp.Department;
import com.mzxssg.yygh.vo.hosp.DepartmentQueryVo;
import com.mzxssg.yygh.vo.hosp.DepartmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-28-19:12
 * @Description:
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    //上传科室接口
    @Override
    public void save(Map<String, Object> paramMap) {
        //paramMap 转换department对象
        String paramMapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paramMapString,Department.class);

        //根据医院编号 和 科室编号查询
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(),department.getDepcode());

        //判断
        if (departmentExist!=null){
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    //查询科室接口
    @Override
    public Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo) {
        //创建Pageable对象，设置当前页和每页记录数
        Pageable pageable = PageRequest.of(page-1, limit);//注意：这里的of方法的page是从0开始的，所以page要减1
        //创建Example对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                        .withIgnoreCase(true);
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);

        Example<Department> example = Example.of(department,matcher);

        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }

    //删除科室接口
    @Override
    public void remove(String hoscode, String depcode) {
        //根据医院编号 和 科室编号查询
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department!=null){
            //调用方法删除
            departmentRepository.deleteById(department.getId());
        }
    }

    //根据医院编号，查询医院所有科室列表
    @Override
    public List<DepartmentVo> findDeptTree(String hoscode) {
        //创建list集合，用于最终数据封装
        List<DepartmentVo> result = new ArrayList<>();

        //根据医院编号，查询医院所有科室信息
        Department department = new Department();
        department.setHoscode(hoscode);
        Example<Department> ex = Example.of(department);
        //所有科室的列表信息
        List<Department> departmentList = departmentRepository.findAll(ex);

        //根据大科室编号 bigcode 分组，获取每个大科室里面下级子科室
        Map<String, List<Department>> departmentMap =
                departmentList.stream().collect(Collectors.groupingBy(Department::getBigcode));

        //遍历map集合 departmentMap
        for (Map.Entry<String,List<Department>> entry:departmentMap.entrySet()){
            //大科室编号
            String bigcode = entry.getKey();
            //大科室编号对应的全部数据
            List<Department> department1List = entry.getValue();

            //封装大科室
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(bigcode);
            departmentVo.setDepname(department1List.get(0).getBigname());

            //封装小科室
            List<DepartmentVo> children = new ArrayList<>();
            for (Department department1 : department1List) {
                DepartmentVo departmentVo1 = new DepartmentVo();
                departmentVo1.setDepcode(department1.getDepcode());
                departmentVo1.setDepname(department1.getDepname());
                //封装到list集合
                children.add(departmentVo1);
            }
            //把小科室list集合放到大科室children里面
            departmentVo.setChildren(children);
            //放到最终result里面
            result.add(departmentVo);
        }
        return result;
    }

    //获取科室名称
    @Override
    public String getDepName(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department!=null){
            return department.getDepname();
        }
        return null;

    }

    //根据科室编号，和医院编号，查询科室名称
    @Override
    public Department getDepartment(String hoscode, String depcode) {
        return departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
    }
}
