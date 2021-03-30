package com.mzxssg.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mzxssg.yygh.model.cmn.Dict;
import com.mzxssg.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-19-20:56
 * @Description:
 */
public interface DictService extends IService<Dict> {

    //根据数据id查询子数据列表
    List<Dict> findChildData(Long id);

    //导出数据字典接口
    void exportDictData(HttpServletResponse response);

    //导入数据字典
    void importDictData(MultipartFile file);

    //根据dictcode和value查询
    String getDictName(String dictCode, String value);

    //根据dictCode获取下级节点
    List<Dict> findByDictCode(String dictCode);
}
