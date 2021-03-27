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

    List<Dict> findChildData(Long id);

    void exportDictData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
