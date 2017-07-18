package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.model.vo.ChapterVo;
import com.maven.luanbin.archetype.model.vo.DetailVo;
import org.apache.ibatis.annotations.Param;

public interface DetailMapper {
    public DetailVo getDetailById(@Param("id") Integer id);
    public void addDetail(@Param("detailVo") DetailVo detailVo);
}