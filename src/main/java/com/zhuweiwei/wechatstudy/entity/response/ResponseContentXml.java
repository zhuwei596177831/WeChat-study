package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.constant.XmlKey;
import com.zhuweiwei.wechatstudy.entity.BaseXml;

import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:42:35
 * @description 回复文本消息
 */
public class ResponseContentXml extends BaseXml {
    private static final long serialVersionUID = 6655988495475921084L;
    /**
     * 文本消息内容 是
     */
    private String Content;

    public ResponseContentXml(Map<String, String> xmlMap) {
        setBaseData(xmlMap);
        setMsgType(xmlMap.get(XmlKey.MsgType.getName()));
        this.Content = xmlMap.get(XmlKey.Content.getName());
    }


    public String getContent() {
        return Content;
    }

    @JacksonXmlProperty(localName = "Content")
    public void setContent(String content) {
        Content = content;
    }
}
