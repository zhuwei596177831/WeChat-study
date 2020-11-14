package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseXml;

import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:07:57
 * @description 回复图片消息
 */
public class ResponseImageXml extends BaseXml {
    private static final long serialVersionUID = -5346900240623525185L;
    /**
     * 图片信息 是
     */
    private BaseImage Image;

    public ResponseImageXml(BaseImage image, Map<String, String> xmlMap, String MsgType) {
        Image = image;
        setBaseData(xmlMap);
        setMsgType(MsgType);
    }

    public BaseImage getImage() {
        return Image;
    }

    @JacksonXmlProperty(localName = "Image")
    public void setImage(BaseImage image) {
        Image = image;
    }
}
