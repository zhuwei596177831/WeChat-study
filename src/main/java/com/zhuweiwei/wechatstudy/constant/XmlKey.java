package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-07 20:23:08
 * @description xml报文枚举
 */
public enum XmlKey {
    ToUserName("ToUserName", "开发者微信号"),
    FromUserName("FromUserName", "发送方帐号（一个OpenID）"),
    CreateTime("CreateTime", "消息创建时间 （整型）"),
    MsgType("MsgType", "消息类型，文本为text"),
    Content("Content", "文本消息内容"),
    MsgId("MsgId", "消息id，64位整型"),
    PicUrl("PicUrl", "图片链接（由系统生成）"),
    Image("Image", "Image"),
    MediaId("MediaId", "图片消息媒体id，可以调用获取临时素材接口拉取数据。"),
    Event("Event", "事件类型"),
    EventKey("EventKey", "事件KEY值或者设置的跳转URL"),
    ;
    private final String name;
    private final String description;

    XmlKey(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
