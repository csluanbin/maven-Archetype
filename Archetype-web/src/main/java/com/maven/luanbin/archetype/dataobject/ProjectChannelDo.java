package com.maven.luanbin.archetype.dataobject;

import java.util.Date;

/**
 * Created by luanbin on 17/6/17.
 */
public class ProjectChannelDo {

    private Integer id;

    private Integer projectId;

    private String projectChannel;

    private Date createTime;

    private Date modifyTime;

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

    public String getProjectChannel() {
        return projectChannel;
    }

    public void setProjectChannel(String projectChannel) {
        this.projectChannel = projectChannel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
