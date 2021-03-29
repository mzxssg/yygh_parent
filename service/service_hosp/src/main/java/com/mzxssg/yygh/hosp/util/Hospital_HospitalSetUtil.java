package com.mzxssg.yygh.hosp.util;

import com.mzxssg.yygh.common.utils.HttpRequestHelper;
import com.mzxssg.yygh.common.utils.MD5;
import com.mzxssg.yygh.common.utils.ResultCodeEnum;
import com.mzxssg.yygh.common.utils.YyghException;
import com.mzxssg.yygh.hosp.service.HospitalSetService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-28-19:26
 * @Description:
 */
public class Hospital_HospitalSetUtil {

    //获取传递过来的信息
    public static Map<String, Object> getParamMap(HttpServletRequest request){
        Map<String, String[]> requestMap =  request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        return paramMap;
    }

    //判断签名是否一致
    public static void judgeConsistent(Map<String, Object> paramMap, HospitalSetService hospitalSetService){
        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编号，查询数据库，查询签名
        String hoscode = (String)paramMap.get("hoscode");
        String signKey = hospitalSetService.getSignKey(hoscode);

        //3 把数据库查询出来的签名进行MD5加密
        String signKeyMD5 = MD5.encrypt(signKey);

        //4 判断签名是否一样
        if (!hospSign.equals(signKeyMD5)){
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
    }
}
