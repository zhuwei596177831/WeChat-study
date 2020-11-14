package com.zhuweiwei.wechatstudy.entity.receive;

import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:45:49
 * @description 接收语音数据
 */
public class ReceiveVoiceXml extends BaseXml {
    private static final long serialVersionUID = -2461425049189348081L;
    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
    /**
     * 消息id，64位整型
     */
    private String MsgId;
    /**
     * 语音识别结果，UTF8编码
     */
    private String Recognition;

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
