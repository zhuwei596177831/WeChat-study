package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:13:49
 * @description 回复视频消息
 */
public class ResponseVideoXml extends BaseXml {
    private static final long serialVersionUID = -273049023993543552L;
    /**
     * 视频信息 是
     */
    private BaseVideo Video;

    public BaseVideo getVideo() {
        return Video;
    }

    @JacksonXmlProperty(localName = "Video")
    public void setVideo(BaseVideo video) {
        Video = video;
    }
}
