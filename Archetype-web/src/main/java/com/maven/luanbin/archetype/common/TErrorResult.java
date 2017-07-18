package com.maven.luanbin.archetype.common;

/**
 * Created by luanbin on 17/6/15.
 */
public class TErrorResult implements TResponse {

    public Integer status;
    public String msg;

    public TErrorResult() {
        this.status = ERROR;
        this.msg = "";
    }

    public TErrorResult(RuntimeException e) {
        this.status = ERROR;
        this.msg = e.getMessage();
    }

    public TErrorResult(int code, String message) {
        this.msg = message;
        this.status = ERROR;
    }

    public TErrorResult(String message) {
        this(500, message);
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
