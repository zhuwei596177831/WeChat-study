package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-21 14:50:15
 * @description
 */
public enum GenderType {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;
    private final Integer gender;
    private final String desc;

    GenderType(Integer gender, String desc) {
        this.gender = gender;
        this.desc = desc;
    }

    public static String getDesc(Integer gender) {
        for (GenderType type : GenderType.values()) {
            if (type.getGender().equals(gender)) {
                return type.getDesc();
            }
        }
        return UNKNOWN.desc;
    }

    public Integer getGender() {
        return gender;
    }

    public String getDesc() {
        return desc;
    }
}
