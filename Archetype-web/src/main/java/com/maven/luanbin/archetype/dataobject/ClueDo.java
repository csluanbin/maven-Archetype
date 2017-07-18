package com.maven.luanbin.archetype.dataobject;

import java.util.Date;

/**
 * Created by luanbin on 17/6/17.
 */
public class ClueDo {

    private Integer id;

    private Integer projectId;

    private Integer projectChannelId;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
