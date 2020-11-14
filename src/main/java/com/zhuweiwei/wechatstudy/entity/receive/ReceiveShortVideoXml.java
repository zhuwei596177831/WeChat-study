package com.zhuweiwei.wechatstudy.entity.receive;

import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:58:28
 * @description 接收小视频数据
 */
public class ReceiveShortVideoXml extends BaseXml {
    private static final long serialVersionUID = -5965859562565377421L;
    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String ThumbMediaId;
    /**
     * 消息id，64位整型
     */
    private String MsgId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
