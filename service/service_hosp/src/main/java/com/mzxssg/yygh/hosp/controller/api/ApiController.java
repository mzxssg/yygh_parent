package com.mzxssg.yygh.hosp.controller.api;

import com.mzxssg.yygh.common.result.Result;
import com.mzxssg.yygh.hosp.service.DepartmentService;
import com.mzxssg.yygh.hosp.service.HospitalService;
import com.mzxssg.yygh.hosp.service.HospitalSetService;
import com.mzxssg.yygh.hosp.service.ScheduleService;
import com.mzxssg.yygh.hosp.util.Hospital_HospitalSetUtil;
import com.mzxssg.yygh.model.hosp.Department;
import com.mzxssg.yygh.model.hosp.Hospital;
import com.mzxssg.yygh.model.hosp.Schedule;
import com.mzxssg.yygh.vo.hosp.DepartmentQueryVo;
import com.mzxssg.yygh.vo.hosp.ScheduleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-27-21:09
 * @Description:
 */
@RestController
@RequestMapping("/api/hosp")
@Api(tags = "医院信息上传接口")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    //删除排班接口
    @PostMapping("schedule/remove")
    public Result remove(HttpServletRequest request){
        //获取传递过来科室信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //医院编号 和 排班编号
        String hoscode = (String)paramMap.get("hoscode");
        String hosScheduleId = (String)paramMap.get("hosScheduleId");

        //签名校验
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        scheduleService.remove(hoscode,hosScheduleId);
        return Result.ok();
    }

    //查询排班接口
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request){
        //获取传递过来科室信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //科室编号
        String depcode = (String)paramMap.get("depcode");

        //当前页 和 每页记录数
        Integer page = StringUtils.isEmpty(paramMap.get("page"))?1:Integer.parseInt((String)paramMap.get("page"));
        Integer limit = StringUtils.isEmpty(paramMap.get("limit"))?1:Integer.parseInt((String)paramMap.get("limit"));

        //签名校验
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);

        //调用service方法
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page,limit,scheduleQueryVo);
        return Result.ok(pageModel);
    }

    //上传排班接口
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        //获取传递过来科室信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //签名校验
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        scheduleService.save(paramMap);

        return Result.ok();
    }


    //删除科室接口
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        //获取传递过来科室信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //医院编号 和 科室编号
        String hoscode = (String)paramMap.get("hoscode");
        String depcode = (String)paramMap.get("depcode");

        //签名校验
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        departmentService.remove(hoscode,depcode);
        return Result.ok();

    }

    //查询科室接口
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request){
        //获取传递过来科室信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //当前页 和 每页记录数
        Integer page = StringUtils.isEmpty(paramMap.get("page"))?1:Integer.parseInt((String)paramMap.get("page"));
        Integer limit = StringUtils.isEmpty(paramMap.get("limit"))?1:Integer.parseInt((String)paramMap.get("limit"));

        //签名校验
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);

        //调用service方法
        Page<Department> pageModel = departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.ok(pageModel);

    }

    //上传科室接口
    @ApiOperation(value = "上传科室接口")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        //获取传递过来科室信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //判断签名是否一致
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        //调用service的方法
        departmentService.save(paramMap);
        return Result.ok();

    }

    //查询医院
    @ApiOperation(value = "查询医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        //获取传递过来医院信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //判断签名是否一致
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        //获取医院编号
        String hoscode = (String)paramMap.get("hoscode");

        //调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);

        return Result.ok(hospital);
    }

    //上传医院接口
    @ApiOperation(value = "上传医院接口")
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        //获取传递过来医院信息
        Map<String, Object> paramMap = Hospital_HospitalSetUtil.getParamMap(request);

        //判断签名是否一致
        Hospital_HospitalSetUtil.judgeConsistent(paramMap, hospitalSetService);

        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoData = (String)paramMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        paramMap.put("logoData", logoData);

        //调用service的方法
        hospitalService.save(paramMap);
        return Result.ok();

    }
}
