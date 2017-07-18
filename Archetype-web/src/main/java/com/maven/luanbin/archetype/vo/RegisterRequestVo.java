package com.maven.luanbin.archetype.vo;

/**
 * Created by luanbin on 17/6/17.
 */
public class RegisterRequestVo {

    private String loginName;

    private String password;

    private String corpName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
}
