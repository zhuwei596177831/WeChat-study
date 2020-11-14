package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseEntity;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:14:23
 * @description Video通用
 */
public class BaseVideo extends BaseEntity {
    private static final long serialVersionUID = 8274494715828045633L;
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id 是
     */
    private String MediaId;
    /**
     * 视频消息的标题 是
     */
    private String Title;
    /**
     * 视频消息的描述 是
     */
    private String Description;

    public String getMediaId() {
        return MediaId;
    }
    @JacksonXmlProperty(localName = "MediaId")
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return Title;
    }
    @JacksonXmlProperty(localName = "Title")
    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }
    @JacksonXmlProperty(localName = "Description")
    public void setDescription(String description) {
        Description = description;
    }
}
