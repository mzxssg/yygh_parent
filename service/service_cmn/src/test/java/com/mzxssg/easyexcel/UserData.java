package com.mzxssg.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-24-10:01
 * @Description:
 */
@Data
public class UserData {

    @ExcelProperty(value = "用户编号",index = 0)
    private int uid;

    @ExcelProperty(value = "用户名称",index = 1)
    private String username;


}
