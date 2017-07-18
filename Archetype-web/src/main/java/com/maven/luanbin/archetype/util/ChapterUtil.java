package com.maven.luanbin.archetype.util;

import com.maven.luanbin.archetype.model.Chapter;
import com.maven.luanbin.archetype.model.vo.ChapterVo;

/**
 * Created by luanbin on 17/2/14.
 */
public class ChapterUtil {
    public static Chapter convert2Chapter(ChapterVo chapterVo){
        Chapter chapter = new Chapter();
        chapter.setId(chapterVo.getId());
        chapter.setElements(chapterVo.getElements());
        chapter.setEncode(chapterVo.getEncode());
        chapter.setTag(chapterVo.getTag());
        chapter.setType(chapterVo.getType());
        chapter.setUrl(chapterVo.getUrl());
        chapter.setTitle(chapterVo.getTitle());
        return chapter;
    }

    public static ChapterVo convert2ChapterVo(Chapter chapter){
        ChapterVo chapterVo = new ChapterVo();
        chapterVo.setId(chapter.getId());
        chapterVo.setElements(chapter.getElements());
        chapterVo.setEncode(chapter.getEncode());
        chapterVo.setTag(chapter.getTag());
        chapterVo.setType(chapter.getType());
        chapterVo.setUrl(chapter.getUrl());
        chapterVo.setTitle(chapter.getTitle());
        return chapterVo;
    }
}
