package com.maven.luanbin.archetype.common;

/**
 * Created by luanbin on 17/6/15.
 */
/**
 * 返回结果信息
 *
 */
public class TSuccessResult<T> implements TResponse {

    public T data;

    public Integer status;

    public String msg;


    public TSuccessResult() {
        status = SUCCESS;
        msg = "";
    }

    public TSuccessResult(String msg) {
        status = SUCCESS;
        this.msg = msg;
    }

    public TSuccessResult(T data, Integer status) {
        this.data = data;
        this.status = status;
        this.msg = "";
    }

    public TSuccessResult(T data) {
        this.data = data;
        this.status = SUCCESS;
        this.msg = "";
    }

    public TSuccessResult(T data, String msg) {
        this.status = SUCCESS;
        this.data = data;
        this.msg = msg;
    }

    public TSuccessResult(T data, Integer status, String msg) {
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
