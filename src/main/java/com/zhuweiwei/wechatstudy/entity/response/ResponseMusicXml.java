package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:18:57
 * @description 回复音乐消息
 */
public class ResponseMusicXml extends BaseXml {
    private static final long serialVersionUID = -6704946847519232085L;
    /**
     * 音乐信息 是
     */
    private BaseMusic Music;

    public BaseMusic getMusic() {
        return Music;
    }

    @JacksonXmlProperty(localName = "Music")
    public void setMusic(BaseMusic music) {
        Music = music;
    }
}
