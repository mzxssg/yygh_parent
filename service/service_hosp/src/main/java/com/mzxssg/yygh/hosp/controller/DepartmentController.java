package com.mzxssg.yygh.hosp.controller;

import com.mzxssg.yygh.common.result.Result;
import com.mzxssg.yygh.hosp.service.DepartmentService;
import com.mzxssg.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-31-11:22
 * @Description:
 */
@RestController
@RequestMapping("/admin/hosp/department")
@Api(tags = "科室管理")
//@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}
