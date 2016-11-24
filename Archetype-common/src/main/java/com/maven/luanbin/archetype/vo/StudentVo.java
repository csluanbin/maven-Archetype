package com.maven.luanbin.archetype.vo;

public class StudentVo {
    private Integer id;
    private String name;

    public StudentVo() {
    }

    public StudentVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
        this.name = name;
    }
}
