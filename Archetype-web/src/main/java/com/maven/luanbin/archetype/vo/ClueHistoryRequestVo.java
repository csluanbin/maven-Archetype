package com.maven.luanbin.archetype.vo;

/**
 * Created by luanbin on 17/6/23.
 */
public class ClueHistoryRequestVo {

    private Integer clueId;

    private Integer status;

    private String userName;

    private String detail;

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
