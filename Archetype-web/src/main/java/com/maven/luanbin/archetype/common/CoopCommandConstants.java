package com.maven.luanbin.archetype.common;

import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 */
public class CoopCommandConstants {
    /**
     * 该枚举用来表示该物料的审核状态
     * */
    public enum CoopCommand{

        CONTACT_NAME(1,"联系人姓名"),
        PHONE(2,"手机号"),
        LOCATION(3,"省市区县"),
        REASON(4,"申请合作原因")
        ;

        private Integer id;
        private String name;

        CoopCommand(Integer id, String name) {
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

        public static CoopCommand enumOf(int id) {
            for (CoopCommand coopCommand : CoopCommand.values()) {
                if (coopCommand.getId() == id) {
                    return coopCommand;
                }
            }
            return null;
        }
    };
}
