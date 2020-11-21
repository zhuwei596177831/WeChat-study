package com.zhuweiwei.wechatstudy.constant;

/**
 * @author 朱伟伟
 * @date 2020-11-08 15:27:11
 * @description 微信接口枚举
 */
public enum UploadUrl {
    UPLOAD_MEDIA("https://api.weixin.qq.com/cgi-bin/media/upload", "新增临时素材"),
    /**
     * 新增其他类型永久素材
     */
    ADD_MATERIAL("https://api.weixin.qq.com/cgi-bin/material/add_material", "新增其他类型永久素材"),
    ADD_MENU("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=", "创建菜单接口"),
    DELETE_MENU("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=", "删除菜单接口"),
    GET_USER_BY_OPENID("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", "获取用户基本信息（包括UnionID机制）"),
    /**
     * 测试图文
     */
    TODAY_MOVIE("http://v.juhe.cn/movie/movies.today?key=1909a3bfe3d6d4d750fd526543616137&cityid=1", "今日放映影片"),
    /**
     * 新增永久图文素材
     */
    UPLOAD_NEWS("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=", "新增永久图文素材"),
    SEND_ALL("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=", "群发消息"),
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
