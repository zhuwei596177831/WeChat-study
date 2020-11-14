package com.zhuweiwei.wechatstudy.entity.response;

import com.zhuweiwei.wechatstudy.entity.BaseEntity;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:28:09
 * @description 图文消息Item
 */
public class ArticleItem extends BaseEntity {
    private static final long serialVersionUID = -5398048311383289498L;
    /**
     * 图文消息标题 是
     */
    private String Title;
    /**
     * 图文消息描述 是
     */
    private String Description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 是
     */
    private String PicUrl;
    /**
     * 点击图文消息跳转链接 是
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
