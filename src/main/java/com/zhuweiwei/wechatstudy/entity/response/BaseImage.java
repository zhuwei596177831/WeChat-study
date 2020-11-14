package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseEntity;

/**
 * @author 朱伟伟
 * @date 2020-11-11 22:23:18
 * @description Image通用
 */
public class BaseImage extends BaseEntity {
    private static final long serialVersionUID = -8207943394396689819L;
    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。 是
     */
    private String MediaId;

    public BaseImage(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    @JacksonXmlProperty(localName = "MediaId")
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
