package com.mzxssg.yygh.cmn.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mzxssg.yygh.cmn.mapper.DictMapper;
import com.mzxssg.yygh.model.cmn.Dict;
import com.mzxssg.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-24-13:39
 * @Description:
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo, dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
