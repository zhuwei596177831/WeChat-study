package com.zhuweiwei.wechatstudy.entity.response;

import com.zhuweiwei.wechatstudy.entity.BaseEntity;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-11-14 20:30:44
 * @description 图文消息Articles
 */
public class Articles extends BaseEntity {
    private static final long serialVersionUID = 3694176952977628685L;
    /**
     * 多个图文消息Item 是
     */
    private List<ArticleItem> item;

    public List<ArticleItem> getItem() {
        return item;
    }

    public void setItem(List<ArticleItem> item) {
        this.item = item;
    }
}
