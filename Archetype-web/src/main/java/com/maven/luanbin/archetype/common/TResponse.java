package com.maven.luanbin.archetype.common;

/**
 * Created by luanbin on 17/6/15.
 */
public interface TResponse {
    public final static  Integer SUCCESS = 200;
    public final static  Integer ERROR = 500;

    public Integer getStatus();
}
