package com.maven.luanbin.archetype.vo;

import com.maven.luanbin.archetype.dataobject.ActivityDo;

import java.util.List;

/**
 * Created by luanbin on 17/6/21.
 */
public class ClueResVo {
    private Integer clueId;

    private String channelName;

    private Integer channelId;

    private List<ActivityVo> activityVoList;

    private String time;

    private Integer status;

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<ActivityVo> getActivityVoList() {
        return activityVoList;
    }

    public void setActivityVoList(List<ActivityVo> activityVoList) {
        this.activityVoList = activityVoList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}
