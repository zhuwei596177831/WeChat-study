package com.zhuweiwei.wechatstudy.entity.response;

import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-21 14:07:12
 * @description 事件base xml
 */
public class BaseEventXml extends BaseXml {
    private static final long serialVersionUID = -7583504964924836415L;
    /**
     * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、SCAN(扫描带参数二维码事件)、
     * LOCATION(上报地理位置事件)、
     * CLICK(点击菜单拉取消息时的事件推送[用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。])、
     * VIEW(点击菜单跳转链接时的事件推送)
     */
    private String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
