package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.domain.TestDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luanbin on 17/6/15.
 */
public interface TestMapper {

    public List<TestDo> getTestByName(@Param("name") String name);
}
