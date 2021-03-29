package com.mzxssg.yygh.hosp.repository;

import com.mzxssg.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-29-14:27
 * @Description:
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {

    //根据医院编号 和 排班编号查询
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
