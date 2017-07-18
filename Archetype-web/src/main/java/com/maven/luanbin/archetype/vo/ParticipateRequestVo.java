package com.maven.luanbin.archetype.vo;


import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public class ParticipateRequestVo {

    private Integer projectId;

    private Integer channelId;

    private List<ParticipateCommandRequestVo> requestVoList;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public List<ParticipateCommandRequestVo> getRequestVoList() {
        return requestVoList;
    }

    public void setRequestVoList(List<ParticipateCommandRequestVo> requestVoList) {
        this.requestVoList = requestVoList;
    }
}
