package com.zhuweiwei.wechatstudy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.zhuweiwei.wechatstudy.component.RefreshAccessToken;
import com.zhuweiwei.wechatstudy.constant.*;
import com.zhuweiwei.wechatstudy.entity.BaseXml;
import com.zhuweiwei.wechatstudy.entity.response.*;
import com.zhuweiwei.wechatstudy.util.Dom4jXmlUtil;
import com.zhuweiwei.wechatstudy.util.HttpUtil;
import com.zhuweiwei.wechatstudy.util.XStreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-11-14 21:20:22
 * @description
 */
@RestController
public class XmlController {

    private static final Logger logger = LoggerFactory.getLogger(XmlController.class);
    private static final String DEFAULT_RESULT = "";
    private final Map<String, OpenIdUser> openIdUserMap = new HashMap<>();
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    /**
     * @param httpServletRequest:
     * @author: 朱伟伟
     * @date: 2020-11-15 11:31
     * @description: 业务数据返回
     **/
    @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_JSON_VALUE)
    public String xml(HttpServletRequest httpServletRequest) throws IOException {
        String openid = httpServletRequest.getParameter("openid");
        logger.info("消息来源openid：{}", openid);
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        Map<String, String> xmlMap = Dom4jXmlUtil.parseDataFromXml(inputStream);
        String xmlResult;
        BaseXml baseXml;
        String msgType = xmlMap.get(XmlKey.MsgType.getName());
        String Content = xmlMap.get(XmlKey.Content.getName());
        if (MsgType.text.getType().equals(msgType)) {
            if ("闫盼盼".equals(Content)) {
                String result = HttpUtil.postForSystemFile(UploadUrl.UPLOAD_MEDIA.getUrl(),
                        restTemplateBuilder.build(), MsgType.image.getType());
                String media_id = JSON.parseObject(result).getString("media_id");
                BaseImage image = new BaseImage(media_id);
                baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
                xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
            } else {
                baseXml = new ResponseContentXml(xmlMap);
                xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
            }
        } else if (MsgType.image.getType().equals(msgType)) {
            String result = HttpUtil.postForSystemFile(UploadUrl.UPLOAD_MEDIA.getUrl(),
                    restTemplateBuilder.build(), MsgType.image.getType());
            String media_id = JSON.parseObject(result).getString("media_id");
            BaseImage image = new BaseImage(media_id);
            baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
            xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
        } else if (MsgType.event.getType().equals(msgType)) {
            String Event = xmlMap.get(XmlKey.Event.getName());
            if (EventType.CLICK.getType().equals(Event)) {//点击事件
                String eventKey = xmlMap.get(XmlKey.EventKey.getName());
                if (EventKey.ypp.getEventKey().equals(eventKey)) {//闫盼盼
                    String postData = HttpUtil.postForSystemFile(UploadUrl.UPLOAD_MEDIA.getUrl(),
                            restTemplateBuilder.build(), MsgType.image.getType());
                    String media_id = JSON.parseObject(postData).getString("media_id");
                    BaseImage image = new BaseImage(media_id);
                    baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
                    xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
                } else if (EventKey.todayMovie.getEventKey().equals(eventKey)) {//今日影片
                    String data = restTemplateBuilder.build().getForObject(UploadUrl.TODAY_MOVIE.getUrl(), String.class);
                    JSONObject jsonObject = JSON.parseObject(data);
                    JSONArray result = jsonObject.getJSONArray("result");
                    ArrayList<ArticleItem> articleItemList = new ArrayList<>(6);
                    for (int i = 0; i < 6; i++) {
                        int nextInt = new Random().nextInt(result.size());
                        JSONObject object = (JSONObject) result.get(nextInt);
                        String movieId = object.getString("movieId");
                        String movieName = object.getString("movieName");
                        String pic_url = object.getString("pic_url");
                        ArticleItem articleItem = new ArticleItem();
                        articleItem.setTitle(movieName);
                        articleItem.setDescription(movieName);
                        articleItem.setPicUrl(pic_url);
                        articleItem.setUrl(pic_url);
                        articleItemList.add(articleItem);
                        result.remove(nextInt);
                    }
                    baseXml = new ResponseArticlesXml(xmlMap, "6", articleItemList);
                    XStream xStream = XStreamUtil.getXStream(ResponseArticlesXml.class);
                    xStream.alias("item", ArticleItem.class);
                    xmlResult = xStream.toXML(baseXml);
                } else if (EventKey.phone.getEventKey().equals(eventKey)) {
                    baseXml = new ResponseContentXml(xmlMap, "18255181971");
                    xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
                } else if (EventKey.weChat.getEventKey().equals(eventKey)) {
                    baseXml = new ResponseContentXml(xmlMap, "zhuwei596177831");
                    xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
                } else if (EventKey.aliPay.getEventKey().equals(eventKey)) {
                    baseXml = new ResponseContentXml(xmlMap, "18255181971");
                    xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
                } else {
                    baseXml = new ResponseContentXml(xmlMap, "你是不是有病？");
                    xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
                }
            } else if (EventType.VIEW.getType().equals(Event)) {//点击网页类型事件
                xmlResult = DEFAULT_RESULT;
            } else if (EventType.UNSUBSCRIBE.getType().equals(Event)) {//取消订阅
                OpenIdUser openIdUser = openIdUserMap.get(openid);
                logger.info("取消关注本微信公众号的用户信息:\n{}", openIdUser);
                logger.info(openIdUser.getInfo());
                xmlResult = DEFAULT_RESULT;
            } else if (EventType.SUBSCRIBE.getType().equals(Event)) {//订阅
                String url = String.format(UploadUrl.GET_USER_BY_OPENID.getUrl(), RefreshAccessToken.getAccessToken(), openid);
                OpenIdUser openIdUser = restTemplateBuilder.build().getForObject(url, OpenIdUser.class);
                openIdUserMap.put(openid, openIdUser);
                baseXml = new ResponseContentXml(xmlMap, "你个没良心的怎么才来/::D/::D/::D\n感谢这么可爱的你关注这么英俊潇洒的我!");
                xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
            } else {
                baseXml = new ResponseContentXml(xmlMap);
                xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
            }
        } else {
            baseXml = new ResponseContentXml(xmlMap);
            xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
        }
        logger.info("响应xml报文：\n{}", xmlResult);
        return xmlResult;
    }

//    @PostMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
//    public BaseXml xml(HttpServletRequest httpServletRequest) throws IOException {
//        return new BaseXml();
//    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseXml json() {
        BaseXml baseXml = new BaseXml();
        baseXml.setFromUserName("朱伟伟");
        return baseXml;
    }

}
