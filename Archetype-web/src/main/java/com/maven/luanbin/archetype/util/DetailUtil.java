package com.maven.luanbin.archetype.util;

import com.maven.luanbin.archetype.model.Chapter;
import com.maven.luanbin.archetype.model.Detail;
import com.maven.luanbin.archetype.model.vo.ChapterVo;
import com.maven.luanbin.archetype.model.vo.DetailVo;

/**
 * Created by luanbin on 17/2/14.
 */
public class DetailUtil {
    public static Detail convert2Detail(DetailVo detailVo){
        Detail detail = new Detail();
        detail.setId(detailVo.getId());
        detail.setElements(detailVo.getElements());
        detail.setEncode(detailVo.getEncode());
        detail.setTag(detailVo.getTag());
        detail.setType(detailVo.getType());
        detail.setChapterId(detailVo.getChapterId());
        return detail;
    }

    public static DetailVo convert2DetailVo(Detail detail){
        DetailVo detailVo = new DetailVo();
        detailVo.setId(detail.getId());
        detailVo.setElements(detail.getElements());
        detailVo.setEncode(detail.getEncode());
        detailVo.setTag(detail.getTag());
        detailVo.setType(detail.getType());
        detailVo.setChapterId(detail.getChapterId());
        return detailVo;
    }
}
