package com.zhuweiwei.wechatstudy.entity;

/**
 * @author 朱伟伟
 * @date 2020-11-11 22:23:18
 * @description
 */
public class Image {
    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;

    public Image(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
