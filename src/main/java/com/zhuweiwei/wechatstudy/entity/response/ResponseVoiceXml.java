package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:10:29
 * @description 回复语音消息
 */
public class ResponseVoiceXml extends BaseXml {
    private static final long serialVersionUID = 4003542026509944901L;
    /**
     * 语音信息 是
     */
    private BaseVoice Voice;

    public BaseVoice getVoice() {
        return Voice;
    }

    @JacksonXmlProperty(localName = "Voice")
    public void setVoice(BaseVoice voice) {
        Voice = voice;
    }
}
