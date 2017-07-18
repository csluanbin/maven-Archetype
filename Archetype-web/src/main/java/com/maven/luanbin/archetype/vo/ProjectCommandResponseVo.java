package com.maven.luanbin.archetype.vo;

/**
 * Created by luanbin on 17/6/17.
 */
public class ProjectCommandResponseVo {
    private Integer projectId;
    private Integer projectCommandId;
    private String projectCommandName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectCommandId() {
        return projectCommandId;
    }

    public void setProjectCommandId(Integer projectCommandId) {
        this.projectCommandId = projectCommandId;
    }

    public String getProjectCommandName() {
        return projectCommandName;
    }

    public void setProjectCommandName(String projectCommandName) {
        this.projectCommandName = projectCommandName;
    }
}
