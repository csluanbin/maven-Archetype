package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.dataobject.ClueDo;
import com.maven.luanbin.archetype.dataobject.ClueHistoryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public interface ClueHistoryMappper {

    public void insert(@Param("clueHistoryDo") ClueHistoryDo clueHistoryDo);

    public List<ClueHistoryDo> getClueHistoryDoByClueId(@Param("clueId") Integer clueId);
}
