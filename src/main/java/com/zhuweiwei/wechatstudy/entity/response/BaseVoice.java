package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseEntity;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:11:32
 * @description Voice通用
 */
public class BaseVoice extends BaseEntity {
    private static final long serialVersionUID = 164666398152136150L;
    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id 是
     */
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }
    @JacksonXmlProperty(localName = "MediaId")
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
