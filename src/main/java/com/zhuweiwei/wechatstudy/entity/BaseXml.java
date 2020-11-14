package com.zhuweiwei.wechatstudy.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.zhuweiwei.wechatstudy.constant.XmlKey;

import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:35:53
 * @description 基础xml
 */
@JacksonXmlRootElement(localName = "xml")
public class BaseXml extends BaseEntity {
    private static final long serialVersionUID = -9183115904660872906L;
    /**
     * 开发者微信号 是
     */
    private String ToUserName;
    /**
     * 发送方帐号（一个OpenID） 是
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型） 是
     */
    private String CreateTime;
    /**
     * 消息类型，文本为text 图片为image 语音为voice 视频为video
     * 小视频为shortvideo 地理位置为location 链接为link
     */
    private String MsgType;

    protected void setBaseData(Map<String, String> xmlMap) {
        this.FromUserName = xmlMap.get(XmlKey.ToUserName.getName());
        this.ToUserName = xmlMap.get(XmlKey.FromUserName.getName());
        this.CreateTime = xmlMap.get(XmlKey.CreateTime.getName());
    }

    public String getToUserName() {
        return ToUserName;
    }

    @JacksonXmlProperty(localName = "ToUserName")
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    @JacksonXmlProperty(localName = "FromUserName")
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    @JacksonXmlProperty(localName = "CreateTime")
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    @JacksonXmlProperty(localName = "MsgType")
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
