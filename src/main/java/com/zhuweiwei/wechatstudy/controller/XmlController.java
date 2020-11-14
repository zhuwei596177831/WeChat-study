package com.zhuweiwei.wechatstudy.controller;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.zhuweiwei.wechatstudy.constant.MsgType;
import com.zhuweiwei.wechatstudy.constant.XmlKey;
import com.zhuweiwei.wechatstudy.entity.BaseXml;
import com.zhuweiwei.wechatstudy.entity.XmlData;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @PostMapping(value = "/xml", produces = MediaType.APPLICATION_JSON_VALUE)
    public String xml(HttpServletRequest httpServletRequest) throws IOException {
        String openid = httpServletRequest.getParameter("openid");
        logger.info("消息来源openid：{}", openid);
        ServletInputStream inputStream = httpServletRequest.getInputStream();
        Map<String, String> xmlMap = Dom4jXmlUtil.parseDataFromXml(inputStream);
        String xmlResult;
        BaseXml baseXml;
        String Content = xmlMap.get(XmlKey.Content.getName());
        String PicUrl = xmlMap.get(XmlKey.PicUrl.getName());
        if ("闫盼盼".equals(Content) || PicUrl != null) {
            String result = HttpUtil.postForSystemFile(restTemplateBuilder.build(), MsgType.image.getType());
            String media_id = JSON.parseObject(result).getString("media_id");
            BaseImage image = new BaseImage(media_id);
            baseXml = new ResponseImageXml(image, xmlMap, MsgType.image.getType());
            xmlResult = XStreamUtil.getXStream(ResponseImageXml.class).toXML(baseXml);
        } else {
            baseXml = new ResponseContentXml(xmlMap);
            xmlResult = XStreamUtil.getXStream(ResponseContentXml.class).toXML(baseXml);
        }
        logger.info("响应xml报文：\n{}", xmlResult);
        return xmlResult;
//        xmlData.reverseFromAndTo();
//        String Content = xmlData.getContent();
//        String picUrl = xmlData.getPicUrl();
//        String resultXml;
//        if ("闫盼盼".equals(Content) || picUrl != null) {
//            String msgType;
//            if (picUrl != null) {
//                msgType = xmlData.getMsgType();
//            } else {
//                msgType = MsgType.image.getType();
//                xmlData.setMsgType(msgType);
//            }
//            String result = HttpUtil.postForSystemFile(restTemplateBuilder.build(), msgType);
//            String media_id = JSON.parseObject(result).getString("media_id");
//            BaseImage image = new BaseImage(media_id);
//            xmlData.setImage(image);
//        } else {
////                if (EventType.CLICK.getType().equals(map.get(XmlKey.Event.getName()))) {
////                    resultXml = Dom4jXmlUtil.generateClickData(map, restTemplateBuilder.build());
////                } else if (EventType.VIEW.getType().equals(map.get(XmlKey.Event.getName()))) {
////                    return false;
////                } else {
////                    resultXml = Dom4jXmlUtil.generateReturnTextData(map);
////                }
//        }
//        resultXml = XStreamUtil.toXml(xmlData);
//        logger.info("返回报文：\n{}", resultXml);
//        outputStream.write(resultXml.getBytes(StandardCharsets.UTF_8));
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
