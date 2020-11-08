package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-08 15:27:11
 * @description 微信接口枚举
 */
public enum UploadUrl {
    UPLOAD_MEDIA("https://api.weixin.qq.com/cgi-bin/media/upload", "新增临时素材"),
    ADD_MENU("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=", "创建菜单接口"),
    ;
    private final String url;
    private final String description;

    UploadUrl(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
