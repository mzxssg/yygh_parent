package com.mzxssg.yygh.hosp.controller;

import com.mzxssg.yygh.common.result.Result;
import com.mzxssg.yygh.hosp.service.HospitalService;
import com.mzxssg.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.HateoasSortHandlerMethodArgumentResolver;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-29-19:25
 * @Description:
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
@Api(tags = "医院管理")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    //医院列表(条件查询分页）
    @ApiOperation(value = "医院列表(条件查询分页）")
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo){
        Page pageModel = hospitalService.selectHospPage(page,limit,hospitalQueryVo);
        return Result.ok(pageModel);
    }
}
