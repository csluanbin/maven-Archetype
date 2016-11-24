package com.maven.luanbin.archetype.service;

import com.maven.luanbin.archetype.domain.Student;
import com.maven.luanbin.archetype.domain.StudentExample;
import com.maven.luanbin.archetype.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuexiaojun on 15/10/31.
 */
@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    public List<Student> getByClassrommId(Integer classroomId) {

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andClassroomIdEqualTo(classroomId);

        return studentMapper.selectByExample(studentExample);

    }

    public Student getById(Integer studentId) {

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andIdEqualTo(studentId);

        List<Student> students = studentMapper.selectByExample(studentExample);

        return CollectionUtils.isEmpty(students) ? null : students.get(0);
    }
}
