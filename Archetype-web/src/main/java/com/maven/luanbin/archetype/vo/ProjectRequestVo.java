package com.maven.luanbin.archetype.vo;

import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public class ProjectRequestVo {

    private String name;

    private List<Integer> coopCommandIdList;

    private String channelName;

    private Integer userId;

    private Integer projectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCoopCommandIdList() {
        return coopCommandIdList;
    }

    public void setCoopCommandIdList(List<Integer> coopCommandIdList) {
        this.coopCommandIdList = coopCommandIdList;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
