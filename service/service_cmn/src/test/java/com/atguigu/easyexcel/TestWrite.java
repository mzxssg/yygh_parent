package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.mzxssg.yygh.model.acl.User;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-24-10:28
 * @Description:
 */

public class TestWrite {

    public static void main(String[] args) {

        //构建数据list集合
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData userData = new UserData();
            userData.setUid(i);
            userData.setUsername("lucy"+i);
             list.add(userData);
        }

        //设置excel文件路径和文件名称
        String fileName = "E:\\excel\\01.xlsx";

        //调用方法实现写操作
        EasyExcel.write(fileName, UserData.class).sheet("用户信息")
                .doWrite(list);

    }
}
