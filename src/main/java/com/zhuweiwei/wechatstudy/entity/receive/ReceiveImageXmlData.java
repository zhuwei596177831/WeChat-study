package com.zhuweiwei.wechatstudy.entity.receive;

import com.zhuweiwei.wechatstudy.entity.BaseXmlData;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:44:13
 * @description 接收图片数据
 */
public class ReceiveImageXmlData extends BaseXmlData {
    private static final long serialVersionUID = -3355001575286802358L;
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;
    /**
     * 请求图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;
    /**
     * 消息id，64位整型
     */
    private String MsgId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
