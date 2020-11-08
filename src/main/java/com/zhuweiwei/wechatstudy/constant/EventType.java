package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-08 16:22:46
 * @description 事件类型
 */
public enum EventType {
    CLICK("CLICK", "点击类型"),
    VIEW("VIEW", "网页类型"),
    MINIPROGRAM("MINIPROGRAM", "小程序类型"),
    ;
    private final String type;
    private final String description;

    EventType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
