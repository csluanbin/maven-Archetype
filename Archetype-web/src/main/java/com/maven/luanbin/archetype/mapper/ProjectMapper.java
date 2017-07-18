package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.dataobject.ProjectChannelDo;
import com.maven.luanbin.archetype.dataobject.ProjectCommandDo;
import com.maven.luanbin.archetype.dataobject.ProjectDo;
import com.maven.luanbin.archetype.dataobject.UserDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public interface ProjectMapper {
    public void insertProject(@Param("projectDo")ProjectDo projectDo);

    public void insertProjectCommand(ProjectCommandDo projectCommandDo);

    public void insertProjectChannel(ProjectChannelDo projectChannelDo);

    public List<ProjectDo> getProjectByUserId(@Param("userId")Integer userId);

    public List<ProjectCommandDo> getProjectCommandByProjectId(@Param("projectId")Integer projectId);

    public void deleteCommandByProjectId(@Param("projectId")Integer projectId);

    public List<ProjectChannelDo> getProjectChannelByProjectId(@Param("projectId")Integer projectId);

    public ProjectChannelDo getProjectChannelByChannelId(@Param("channelId")Integer channelId);

    public ProjectDo getProjectById(@Param("projectId")Integer projectId);
}
