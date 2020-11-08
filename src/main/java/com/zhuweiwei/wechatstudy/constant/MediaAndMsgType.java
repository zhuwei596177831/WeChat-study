package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-08 16:22:46
 * @description
 */
public enum MediaAndMsgType {
    image("image", "image"),
    voice("voice", "voice"),
    video("video", "video"),
    thumb("thumb", "thumb"),
    link("link", "link"),
    event("event", "event"),
    text("text", "text"),
    ;
    private final String type;
    private final String description;

    MediaAndMsgType(String type, String description) {
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
