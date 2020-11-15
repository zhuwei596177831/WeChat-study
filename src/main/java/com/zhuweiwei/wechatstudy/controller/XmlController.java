package com.zhuweiwei.wechatstudy.controller;

import com.alibaba.fastjson.JSON;
import com.zhuweiwei.wechatstudy.constant.EventKey;
import com.zhuweiwei.wechatstudy.constant.EventType;
import com.zhuweiwei.wechatstudy.constant.MsgType;
import com.zhuweiwei.wechatstudy.constant.XmlKey;
import com.zhuweiwei.wechatstudy.entity.BaseXml;
import com.zhuweiwei.wechatstudy.entity.response.BaseImage;
import com.zhuweiwei.wechatstudy.entity.response.ResponseContentXml;
import com.zhuweiwei.wechatstudy.entity.response.ResponseImageXml;
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
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-11-14 21:20:22
 * @description
 */
@RestController
public class XmlController {

    private static final Logger logger = LoggerFactory.getLogger(XmlController.class);
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
                String result = HttpUtil.postForSystemFile(restTemplateBuilder.build(), MsgType.image.getType());
                String media_id = JSON.parseObject(result).getString("media_id");
                BaseImage image = new BaseImage(media_id);
                baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
                xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
            } else {
                baseXml = new ResponseContentXml(xmlMap);
                xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
            }
        } else if (MsgType.image.getType().equals(msgType)) {
            String result = HttpUtil.postForSystemFile(restTemplateBuilder.build(), MsgType.image.getType());
            String media_id = JSON.parseObject(result).getString("media_id");
            BaseImage image = new BaseImage(media_id);
            baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
            xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
        } else if (MsgType.event.getType().equals(msgType)) {
            String Event = xmlMap.get(XmlKey.Event.getName());
            if (EventType.CLICK.getType().equals(Event)) {
                String eventKey = xmlMap.get(XmlKey.EventKey.getName());
                if (EventKey.featuredPicture.getEventKey().equals(eventKey)) {
                    String postData = HttpUtil.postForSystemFile(restTemplateBuilder.build(), MsgType.image.getType());
                    String media_id = JSON.parseObject(postData).getString("media_id");
                    BaseImage image = new BaseImage(media_id);
                    baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
                    xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
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
            } else if (EventType.VIEW.getType().equals(Event)) {
                xmlResult = null;
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
