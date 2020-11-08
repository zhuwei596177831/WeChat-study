package com.zhuweiwei.wechatstudy.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-07 16:42:22
 * @description
 */
@Component
@ConfigurationProperties(prefix = "we-chat")
public class WeChatProperties {
    private String appID;
    private String appsecret;
    private String accessTokenUrl;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }
}
