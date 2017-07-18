package com.maven.luanbin.archetype.service.detail;

import com.maven.luanbin.archetype.mapper.ChapterMapper;
import com.maven.luanbin.archetype.mapper.DetailMapper;
import com.maven.luanbin.archetype.model.Chapter;
import com.maven.luanbin.archetype.model.Detail;
import com.maven.luanbin.archetype.model.vo.ChapterVo;
import com.maven.luanbin.archetype.model.vo.DetailVo;
import com.maven.luanbin.archetype.util.ChapterUtil;
import com.maven.luanbin.archetype.util.DetailUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by luanbin on 17/2/14.
 */
@Service
public class DetailService {

    @Resource
    private DetailMapper detailMapper;

    public void addDetail(Detail detail){
        DetailVo detailVo = DetailUtil.convert2DetailVo(detail);
        detailMapper.addDetail(detailVo);
    }

    public Detail getDetailById(Integer id){
        DetailVo detailVo = detailMapper.getDetailById(id);
        return DetailUtil.convert2Detail(detailVo);
    }
}
