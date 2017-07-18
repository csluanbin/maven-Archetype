package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.dataobject.ActivityDo;
import com.maven.luanbin.archetype.dataobject.UserDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public interface ActivityMappper {

    public void insertActivity(ActivityDo activityDo);

    public List<ActivityDo> getActivityByClueId(@Param("clueId") Integer clueId);
}
