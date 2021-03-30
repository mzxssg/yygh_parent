package com.mzxssg.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mzxssg.yygh.cmn.listen.DictListener;
import com.mzxssg.yygh.cmn.mapper.DictMapper;
import com.mzxssg.yygh.cmn.service.DictService;
import com.mzxssg.yygh.model.cmn.Dict;

import com.mzxssg.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zexin Ma
 * @Create: 2021-03-20-10:09
 * @Description:
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    //根据数据id查询子数据列表
    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            boolean isChild = isChildren(dictId);
            dict.setHasChildren(isChild);
        }
        return dictList;
    }

    //导出数据字典接口
    @Override
    public void exportDictData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("数据字典", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
        List<Dict> dicts = baseMapper.selectList(null);
        //将Dict转换为DictEevo\
        List<DictEeVo> dictEeVoList= new ArrayList<>();
        for (Dict dict : dicts) {
            DictEeVo dictEeVo = new DictEeVo();
            BeanUtils.copyProperties(dict, dictEeVo);
            dictEeVoList.add(dictEeVo);
        }
        //调用方法进行写操作
        try {
            EasyExcel.write(response.getOutputStream(), DictEeVo.class)
                    .sheet("dict")
                    .doWrite(dictEeVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //导入数据字典
    @Override
    @CacheEvict(value = "dict",allEntries = true)
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDictName(String dictCode, String value) {
        //如果dictCode为空，直接根据value查询
        if (StringUtils.isEmpty(dictCode)){
            QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("value", value);
            Dict dict = baseMapper.selectOne(queryWrapper);
            return dict.getName();
        }else {//如果dictCode不为空，直接根据dictCode和value查询
            //根据dictcode查询dict对象，得到dict的id值
            Dict codeDict = this.getDictByDictCode(dictCode);

            Long parent_id = codeDict.getId();
            //根据parent_id和value进行查询
            Dict dict = baseMapper.selectOne(new QueryWrapper<Dict>()
                    .eq("parent_id", parent_id)
                    .eq("value", value));
            return dict.getName();
        }

    }

    //根据dictCode获取下级节点
    @Override
    public List<Dict> findByDictCode(String dictCode) {

        //根据dictcode获取对应id;
        Dict dict = this.getDictByDictCode(dictCode);
        //根据id获取子节点
        List<Dict> childData = this.findChildData(dict.getId());

        return childData;
    }

    //根据dictcode查询dict对象，得到dict的id值(抽取的方法)
    private Dict getDictByDictCode(String dictCode){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_code", dictCode);
        Dict codeDict = baseMapper.selectOne(queryWrapper);
        return codeDict;
    }

    //判断id下面是否有子节点
    private boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }
}
