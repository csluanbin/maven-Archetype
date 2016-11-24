package com.maven.luanbin.archetype.model;

import com.maven.luanbin.archetype.domain.Classroom;
import com.maven.luanbin.archetype.domain.Student;

import java.util.List;

/**
 * Created by wyb on 15/11/7.
 */
public class ClassroomModel extends Classroom {

    private List<Student>  students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
