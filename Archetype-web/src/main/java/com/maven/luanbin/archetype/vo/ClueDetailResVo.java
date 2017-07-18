package com.maven.luanbin.archetype.vo;

import java.util.List;

/**
 * Created by luanbin on 17/6/23.
 */
public class ClueDetailResVo {

    private Integer clueId;

    private List<ActivityVo> activityVoList;

    private String createTime;

    private Integer status;

    private List<ClueHistoryResVo> clueHistoryResVoList;

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    public List<ActivityVo> getActivityVoList() {
        return activityVoList;
    }

    public void setActivityVoList(List<ActivityVo> activityVoList) {
        this.activityVoList = activityVoList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ClueHistoryResVo> getClueHistoryResVoList() {
        return clueHistoryResVoList;
    }

    public void setClueHistoryResVoList(List<ClueHistoryResVo> clueHistoryResVoList) {
        this.clueHistoryResVoList = clueHistoryResVoList;
    }
}
