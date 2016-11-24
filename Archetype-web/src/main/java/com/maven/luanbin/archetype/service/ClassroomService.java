package com.maven.luanbin.archetype.service;

import com.maven.luanbin.archetype.mapper.ClassroomExtMapper;
import com.maven.luanbin.archetype.model.ClassroomModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuexiaojun on 15/10/31.
 */
@Service
public class ClassroomService {
    @Resource
    private ClassroomExtMapper classroomExtMapper;

    public List<ClassroomModel> listAll() {
        return classroomExtMapper.getClassroomModels(null, null, null, null);
    }
}
