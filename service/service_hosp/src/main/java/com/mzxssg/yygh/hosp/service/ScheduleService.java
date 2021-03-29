package com.mzxssg.yygh.hosp.service;

import com.mzxssg.yygh.model.hosp.Schedule;
import com.mzxssg.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-29-14:29
 * @Description:
 */
public interface ScheduleService {
    //上传排班接口
    void save(Map<String, Object> paramMap);

    //查询排班接口
    Page<Schedule> findPageSchedule(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班接口
    void remove(String hoscode, String hosScheduleId);
}
