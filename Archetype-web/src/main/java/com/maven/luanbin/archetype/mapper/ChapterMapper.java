package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.domain.Student;
import com.maven.luanbin.archetype.domain.StudentExample;
import com.maven.luanbin.archetype.model.vo.ChapterVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    public ChapterVo getChapterById(@Param("id")Integer id);

    public void addChapter(@Param("chapterVo")ChapterVo chapterVo);

    public List<ChapterVo> getAllChapter();

}