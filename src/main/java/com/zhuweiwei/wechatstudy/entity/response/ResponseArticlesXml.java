package com.zhuweiwei.wechatstudy.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:26:18
 * @description 回复图文消息
 */
public class ResponseArticlesXml extends BaseXml {
    private static final long serialVersionUID = -4690501553437586081L;
    /**
     * 图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，
     * 开发者只能回复1条图文消息；其余场景最多可回复8条图文消息 是
     */
    private String ArticleCount;
    /**
     * 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数 是
     */
    private Articles Articles;

    public String getArticleCount() {
        return ArticleCount;
    }
    @JacksonXmlProperty(localName = "ArticleCount")
    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }

    public com.zhuweiwei.wechatstudy.entity.response.Articles getArticles() {
        return Articles;
    }
    @JacksonXmlProperty(localName = "Articles")
    public void setArticles(com.zhuweiwei.wechatstudy.entity.response.Articles articles) {
        Articles = articles;
    }
}
