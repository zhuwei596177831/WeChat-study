package com.zhuweiwei.wechatstudy.entity.receive;

import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:51:30
 * @description 接收视频数据
 */
public class ReceiveVideoXml extends BaseXml {
    private static final long serialVersionUID = 57195176985721809L;
    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
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
