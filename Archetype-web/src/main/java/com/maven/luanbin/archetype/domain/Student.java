package com.maven.luanbin.archetype.domain;

public class Student {
    /**
     * 学生ID
     */
    private Integer id;

    /**
     * 学生名字
     */
    private String name;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     * 班级ID
     */
    private Integer classroomId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }
}