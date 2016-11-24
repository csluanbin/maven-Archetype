package com.maven.luanbin.archetype.mapper;

import com.maven.luanbin.archetype.model.ClassroomModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wyb on 15/11/7.
 */
public interface ClassroomExtMapper {
    List<ClassroomModel> getClassroomModels(
            @Param(value = "classroomId") Integer classroomId,
            @Param(value = "className") String className,
            @Param(value = "studentName") String studentName,
            @Param(value = "studentAge") Integer studentAge
    );
}
