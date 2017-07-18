package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.dataobject.UserDo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by luanbin on 17/6/17.
 */
public interface UserMappper {

    public UserDo getUserDoByLoginName(@Param("loginName")String loginName);

    public void insert(UserDo userDo);

    public UserDo getUserDoById(@Param("id")Integer id);
}
