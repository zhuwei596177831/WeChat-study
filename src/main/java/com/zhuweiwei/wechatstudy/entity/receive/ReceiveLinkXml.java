package com.zhuweiwei.wechatstudy.entity.receive;

import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-13 19:04:51
 * @description 接收链接数据
 */
public class ReceiveLinkXml extends BaseXml {
    private static final long serialVersionUID = 5190669152915972406L;
    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;
    /**
     * 消息id，64位整型
     */
    private String MsgId;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
