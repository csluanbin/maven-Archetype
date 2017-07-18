package com.maven.luanbin.archetype.common;

/**
 * Created by luanbin on 17/6/17.
 */
public enum AuditEnum {
    SUSPEND(1,"未处理"),
    DUPLICATE(2,"重复线索"),
    INVALID(3,"无效线索"),
    FOLLOWING(4,"机会跟进中"),
    COOP(5,"达成合作")
    ;

    private Integer id;
    private String name;

    AuditEnum(Integer id, String name) {
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

    public static AuditEnum enumOf(int id) {
        for (AuditEnum auditEnum : AuditEnum.values()) {
            if (auditEnum.getId() == id) {
                return auditEnum;
            }
        }
        return null;
    }
}
