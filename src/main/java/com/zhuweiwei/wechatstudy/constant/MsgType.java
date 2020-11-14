package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-08 16:22:46
 * @description 消息类型
 */
public enum MsgType {
    text("text", "文本"),
    image("image", "图片"),
    voice("voice", "语音"),
    video("video", "视频"),
    shortvideo("shortvideo", "小视频"),
    location("location", "地理位置"),
    link("link", "链接"),
    music("music", "音乐"),
    news("news", "图文"),
    event("event", "事件"),
    ;
    private final String type;
    private final String description;

    MsgType(String type, String description) {
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
