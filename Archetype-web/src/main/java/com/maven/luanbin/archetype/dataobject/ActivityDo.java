package com.maven.luanbin.archetype.dataobject;

import java.util.Date;

/**
 * Created by luanbin on 17/6/17.
 */
public class ActivityDo {

    private Integer id;

    private Integer projectId;

    private Integer projectChannelId;

    private Integer clueId;

    private Integer commandId;

    private String commandInfo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectChannelId() {
        return projectChannelId;
    }

    public void setProjectChannelId(Integer projectChannelId) {
        this.projectChannelId = projectChannelId;
    }

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }

    public String getCommandInfo() {
        return commandInfo;
    }

    public void setCommandInfo(String commandInfo) {
        this.commandInfo = commandInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
