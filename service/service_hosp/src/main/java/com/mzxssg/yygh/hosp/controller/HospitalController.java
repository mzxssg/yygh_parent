package com.mzxssg.yygh.hosp.controller;

import com.mzxssg.yygh.common.result.Result;
import com.mzxssg.yygh.hosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-29-19:25
 * @Description:
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

//    //医院列表(条件查询分页）
//    @GetMapping("list/{page}/{limit}")
//    public Result listHosp(@PathVariable long page,
//                           @PathVariable long limit){
//
//    }
}
