package com.mzxssg.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-24-10:47
 * @Description:
 */
public class TestRead {
    public static void main(String[] args) {
        //读取文件路径
        String fileName = "E:\\excel\\01.xlsx";
        //调用方法实现读取操作
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
