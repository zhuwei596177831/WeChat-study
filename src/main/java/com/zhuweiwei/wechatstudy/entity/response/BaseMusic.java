package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseEntity;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:19:43
 * @description Music通用
 */
public class BaseMusic extends BaseEntity {
    private static final long serialVersionUID = -8803876253665778390L;
    /**
     * 音乐标题 否
     */
    private String Title;
    /**
     * 音乐描述 否
     */
    private String Description;
    /**
     * 音乐链接 否
     */
    private String MusicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐 否
     */
    private String HQMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id 是
     */
    private String ThumbMediaId;

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

    public String getMusicUrl() {
        return MusicUrl;
    }
    @JacksonXmlProperty(localName = "MusicUrl")
    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }
    @JacksonXmlProperty(localName = "HQMusicUrl")
    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }
    @JacksonXmlProperty(localName = "ThumbMediaId")
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
