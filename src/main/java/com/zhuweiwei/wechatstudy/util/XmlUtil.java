package com.zhuweiwei.wechatstudy.util;

import com.alibaba.fastjson.JSON;
import com.zhuweiwei.wechatstudy.constant.EventKey;
import com.zhuweiwei.wechatstudy.constant.MediaAndMsgType;
import com.zhuweiwei.wechatstudy.constant.XmlKey;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-11-07 19:13:32
 * @description
 */
public class XmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);


    public static Element generateXmlElement(Map<String, String> map) {
        Element rootElement = new DefaultElement("xml");
        rootElement.addElement(XmlKey.ToUserName.getName()).addCDATA(map.get(XmlKey.FromUserName.getName()));
        rootElement.addElement(XmlKey.FromUserName.getName()).addCDATA(map.get(XmlKey.ToUserName.getName()));
        rootElement.addElement(XmlKey.CreateTime.getName()).addText(map.get(XmlKey.CreateTime.getName()));
        return rootElement;
    }

    /**
     * @param map:
     * @author: 朱伟伟
     * @date: 2020-11-07 20:28
     * @description: 生成普通文本消息
     **/
    public static String generateReturnTextData(Map<String, String> map) {
        Element xmlElement = generateXmlElement(map);
        xmlElement.addElement(XmlKey.MsgType.getName()).addCDATA(map.get(XmlKey.MsgType.getName()));
        xmlElement.addElement(XmlKey.Content.getName()).addCDATA(map.get(XmlKey.Content.getName()));
        return xmlElement.asXML();
    }

    public static String generateReturnTextData(Map<String, String> map, String content) {
        Element xmlElement = generateXmlElement(map);
        xmlElement.addElement(XmlKey.MsgType.getName()).addCDATA(MediaAndMsgType.text.getType());
        xmlElement.addElement(XmlKey.Content.getName()).addCDATA(content);
        return xmlElement.asXML();
    }

    /**
     * @param map:
     * @author: 朱伟伟
     * @date: 2020-11-08 15:17
     * @description: 生成图片消息消息
     **/
    public static String generateImageData(Map<String, String> map, String mediaId) {
        Element xmlElement = generateXmlElement(map);
        xmlElement.addElement(XmlKey.MsgType.getName()).addCDATA(MediaAndMsgType.image.getType());
        Element imageElement = xmlElement.addElement(XmlKey.Image.getName());
        imageElement.addElement(XmlKey.MediaId.getName()).addCDATA(mediaId);
        return xmlElement.asXML();
    }

    public static String generateClickData(Map<String, String> map, RestTemplate restTemplate) {
        String result;
        String eventKey = map.get(XmlKey.EventKey.getName());
        if (EventKey.featuredPicture.getEventKey().equals(eventKey)) {
            String postData = HttpUtils.postForSystemFile(restTemplate, MediaAndMsgType.image.getType());
            String media_id = JSON.parseObject(postData).getString("media_id");
            result = generateImageData(map, media_id);
        } else if (EventKey.phone.getEventKey().equals(eventKey)) {
            result = generateReturnTextData(map, "18255181971");
        } else if (EventKey.weChat.getEventKey().equals(eventKey)) {
            result = generateReturnTextData(map, "zhuwei596177831");
        } else if (EventKey.aliPay.getEventKey().equals(eventKey)) {
            result = generateReturnTextData(map, "18255181971");
        } else {
            result = "你是不是有病？";
        }
        return result;
    }

    /**
     * @param inputStream:
     * @author: 朱伟伟
     * @date: 2020-11-07 20:29
     * @description: 解析xml报文 封装到map中
     **/
    public static Map<String, String> parseDataFromXml(InputStream inputStream) {
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.error("解析xml报文失败：{}", e.getMessage());
            return new HashMap<>();
        }
        Element xmlElement = document.getRootElement();
        logger.info("请求报文：\n{}", xmlElement.asXML());
        Map<String, String> map = new LinkedHashMap<>(16);
        Iterator iterator = xmlElement.elementIterator();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            map.put(element.getName(), element.getStringValue());
        }
        return map;
    }


}
