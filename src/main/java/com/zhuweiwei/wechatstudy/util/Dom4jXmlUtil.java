package com.zhuweiwei.wechatstudy.util;

import com.alibaba.fastjson.JSON;
import com.zhuweiwei.wechatstudy.constant.EventKey;
import com.zhuweiwei.wechatstudy.constant.MsgType;
import com.zhuweiwei.wechatstudy.constant.XmlKey;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-11-07 19:13:32
 * @description
 */
public class Dom4jXmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(Dom4jXmlUtil.class);

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
            Element xmlElement = document.getRootElement();
            logger.info("请求xml报文：\n{}", xmlElement.asXML());
            Map<String, String> map = new LinkedHashMap<>(16);
            Iterator iterator = xmlElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                map.put(element.getName(), element.getStringValue());
            }
            return map;
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.error("解析xml报文失败：{}", e.getMessage());
            return new HashMap<>();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
