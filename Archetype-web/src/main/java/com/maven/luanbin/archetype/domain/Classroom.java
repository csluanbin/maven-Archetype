package com.maven.luanbin.archetype.domain;

public class Classroom {
    /**
     * 唯一ID
     */
    private Integer id;

    /**
     * 班级名字
     */
    private String name;

    /**
     * 班级地址
     */
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}