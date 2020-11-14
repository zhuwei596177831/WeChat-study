package com.zhuweiwei.wechatstudy.entity;

import com.zhuweiwei.wechatstudy.entity.response.BaseImage;

import java.io.Serializable;

/**
 * @author 朱伟伟
 * @date 2020-11-11 21:44:38
 * @description 请求与相应实体
 */
public class XmlData implements Serializable {
    private static final long serialVersionUID = -1485472590297791852L;
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    private String CreateTime;
    /**
     * 消息类型，文本为text
     */
    private String MsgType;
    /**
     * 文本消息内容
     */
    private String Content;
    /**
     * 消息id，64位整型
     */
    private String MsgId;
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;
    /**
     * Image
     */
    private BaseImage Image;
    /**
     * 请求图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;
    /**
     * 事件类型
     */
    private String Event;
    /**
     * 事件KEY值或者设置的跳转URL
     */
    private String EventKey;

    public void reverseFromAndTo() {
        String ToUserName = this.ToUserName;
        this.ToUserName = this.FromUserName;
        this.FromUserName = ToUserName;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public BaseImage getImage() {
        return Image;
    }

    public void setImage(BaseImage image) {
        Image = image;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
