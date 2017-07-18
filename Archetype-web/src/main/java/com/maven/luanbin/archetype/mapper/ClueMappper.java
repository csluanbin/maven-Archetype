package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.dataobject.ClueDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public interface ClueMappper {

    public void insert(@Param("clueDo")ClueDo clueDo);

    public List<ClueDo> getClueDoByProjectId(@Param("projectId") Integer projectId,
                                             @Param("channelId") Integer channelId,
                                             @Param("status") Integer status);

    public void updateStatus(@Param("clueId") Integer clueId, @Param("status") Integer status);

    public ClueDo getClueDoByClueId(@Param("clueId") Integer clueId);
}
