package com.zhuweiwei.wechatstudy.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuweiwei.wechatstudy.properties.WeChatProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2020-11-07 22:21:09
 * @description 刷新access_token
 */
@Component
public class RefreshAccessToken implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(RefreshAccessToken.class);
    private static final Map<String, Object> tokenMap = new ConcurrentHashMap<>();
    private static final long initialDelay = 5;
    private static final long delay = 300;
    @Resource
    RestTemplateBuilder restTemplateBuilder;
    @Resource
    WeChatProperties weChatProperties;

    private void refresh() {
        logger.info("refresh......");
        LocalDateTime invalidDate = (LocalDateTime) tokenMap.get("invalidDate");
        if (invalidDate == null || invalidDate.isBefore(LocalDateTime.now())) {
            logger.info("refresh access_token......");
            String url = String.format(weChatProperties.getAccessTokenUrl(), weChatProperties.getAppID(), weChatProperties.getAppsecret());
            String result = restTemplateBuilder.build().getForObject(url, String.class);
            logger.info("获取access_token返回：\n{}", result);
            JSONObject jsonObject = JSON.parseObject(result);
            tokenMap.put("access_token", jsonObject.getString("access_token"));
            Long expires_in = jsonObject.getLong("expires_in");
            //提前十分钟刷新
            tokenMap.put("invalidDate", LocalDateTime.now().plusSeconds(expires_in - 10 * 60));
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(this::refresh, initialDelay, delay, TimeUnit.SECONDS);
    }

    public static String getAccessToken() {
        return (String) tokenMap.get("access_token");
    }
}
