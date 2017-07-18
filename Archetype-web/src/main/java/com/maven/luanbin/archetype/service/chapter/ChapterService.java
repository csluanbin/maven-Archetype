package com.maven.luanbin.archetype.service.chapter;

import com.maven.luanbin.archetype.mapper.ChapterMapper;
import com.maven.luanbin.archetype.model.Chapter;
import com.maven.luanbin.archetype.model.vo.ChapterVo;
import com.maven.luanbin.archetype.util.ChapterUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luanbin on 17/2/14.
 */
@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public Long addChapter(Chapter chapter){
        ChapterVo chapterVo = ChapterUtil.convert2ChapterVo(chapter);
        chapterMapper.addChapter(chapterVo);
        return chapterVo.getId();
    }

    public Chapter getChapterById(Integer id){
        ChapterVo chapterVo = chapterMapper.getChapterById(id);
        return ChapterUtil.convert2Chapter(chapterVo);
    }

    public List<Chapter> getAllChapter(){
        List<ChapterVo> chapterVoList = chapterMapper.getAllChapter();

        List<Chapter> chapterList = new ArrayList<>();
        for(ChapterVo chapterVo : chapterVoList){
            chapterList.add(ChapterUtil.convert2Chapter(chapterVo));
        }
        return chapterList;
    }
}
