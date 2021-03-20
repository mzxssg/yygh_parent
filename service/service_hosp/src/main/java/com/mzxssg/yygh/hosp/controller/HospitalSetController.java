package com.mzxssg.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzxssg.yygh.common.result.Result;
import com.mzxssg.yygh.hosp.service.HospitalSetService;
import com.mzxssg.yygh.model.hosp.HospitalSet;
import com.mzxssg.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-20-10:26
 * @Description:
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;


    //1 查询医院设置表所有信息
    @ApiOperation(value = "获取所有医院设置的信息")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        //调用service的方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //2 逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if (flag)
            return Result.ok();
        else
            return Result.fail();

    }

    //3 条件查询带分页
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("/findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false ) HospitalSetQueryVo hospitalSetQueryVo){
        //创建page对象，传入当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper();
        String hosname = hospitalSetQueryVo.getHosname();//医院名称
        String hoscode = hospitalSetQueryVo.getHoscode();//医院编号
        if (!StringUtils.isEmpty(hosname)){
            queryWrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        if (!StringUtils.isEmpty(hoscode)){
            queryWrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }

        //调用方法实现分页查询
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);

        return Result.ok(hospitalSetPage);

    }

    //4 添加医院设置

    //5 根据id获取医院设置

    //6 修改医院设置

    //7 批量删除医院设置





}
