package com.zhuweiwei.wechatstudy.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuweiwei.wechatstudy.component.RefreshAccessToken;
import com.zhuweiwei.wechatstudy.constant.MsgType;
import com.zhuweiwei.wechatstudy.constant.UploadUrl;
import com.zhuweiwei.wechatstudy.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 朱伟伟
 * @date 2020-11-21 17:26:28
 * @description
 */
//@Component
public class NewsTask {
//    @Resource
    RestTemplateBuilder restTemplateBuilder;
    private static final Logger logger = LoggerFactory.getLogger(NewsTask.class);

    /**
     * 定时群发消息
     */
//    @Scheduled(cron = "0 49 18 * * ?")
    public void sendNews() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        JSONArray articles = new JSONArray();
        for (int i = 0; i < 2; i++) {
            //上传素材 image video等
            String result = HttpUtil.postForSystemFile(UploadUrl.UPLOAD_MEDIA.getUrl(), restTemplate, MsgType.image.getType());
            logger.info("上传素材返回\n{}", result);
            String media_id = JSON.parseObject(result).getString("media_id");
            JSONObject articleItem = new JSONObject();
            articleItem.put("thumb_media_id", media_id);
            articleItem.put("author", "朱伟伟");
            articleItem.put("title", "闫盼盼《 甜蜜的俘虜 》");
            articleItem.put("content_source_url", "http://www.mtime.com/");
            articleItem.put("content", "闫盼盼《 甜蜜的俘虜 》之" + HttpUtil.fineName);
            articleItem.put("digest", "闫盼盼《 甜蜜的俘虜 》");
            articleItem.put("show_cover_pic", 1);
            articleItem.put("need_open_comment", 1);
            articleItem.put("only_fans_can_comment", 0);
            articles.add(articleItem);
        }
        //上传图文消息素材
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("articles", articles);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String send = jsonObject.toJSONString();
        logger.info("上传图文消息素材发送\n{}", send);
        HttpEntity<String> httpEntity = new HttpEntity<>(send, httpHeaders);
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(UploadUrl.UPLOAD_NEWS.getUrl() + RefreshAccessToken.getAccessToken(), httpEntity, String.class);
        String result = postForEntity.getBody();
        logger.info("上传图文消息素材返回\n{}", result);
        if (postForEntity.getStatusCodeValue() == 200) {
            String media_id = JSON.parseObject(result).getString("media_id");
            //根据标签进行群发
            jsonObject = new JSONObject();
            JSONObject filter = new JSONObject();
            filter.put("is_to_all", true);
            jsonObject.put("filter", filter);
            JSONObject mpnews = new JSONObject();
            mpnews.put("media_id", media_id);
            jsonObject.put("mpnews", mpnews);
            jsonObject.put("msgtype", "mpnews");
            jsonObject.put("send_ignore_reprint", 1);
            send = jsonObject.toJSONString();
            logger.info("根据标签进行群发发送\n{}", send);
            httpEntity = new HttpEntity<>(send, httpHeaders);
            postForEntity = restTemplate.postForEntity(UploadUrl.SEND_ALL.getUrl() + RefreshAccessToken.getAccessToken(), httpEntity, String.class);
            result = postForEntity.getBody();
            logger.info("根据标签进行群发返回\n{}", result);
        }
    }

}
