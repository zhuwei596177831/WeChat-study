package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-08 16:22:46
 * @description 事件类型key 菜单的key
 */
public enum EventKey {
    clickMeNow("clickMeNow", "快点我啊"),
    ypp("ypp", "闫盼盼"),
    todayMovie("todayMovie", "今日影片"),
    contactAuthor("contactAuthor", "联系作者"),
    weChat("weChat", "微信"),
    aliPay("aliPay", "支付宝"),
    phone("phone", "手机号"),
    ;
    private final String eventKey;
    private final String description;

    EventKey(String eventKey, String description) {
        this.eventKey = eventKey;
        this.description = description;
    }

    public String getEventKey() {
        return eventKey;
    }

    public String getDescription() {
        return description;
    }
}
