package com.maven.luanbin.archetype.vo;

/**
 * Created by luanbin on 17/6/17.
 */
public class LoginResponseVo {

    private Integer userId;

    private String corpName;

    private String loginName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
