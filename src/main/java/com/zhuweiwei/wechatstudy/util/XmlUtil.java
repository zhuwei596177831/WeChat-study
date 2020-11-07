package com.zhuweiwei.wechatstudy.util;

import com.zhuweiwei.wechatstudy.constant.XmlKeyEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-11-07 19:13:32
 * @description
 */
public class XmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public static Element generateXmlElement(Map<String, String> map) {
        Element xmlElement = new DefaultElement("xml");
        xmlElement.addElement(XmlKeyEnum.ToUserName.getName()).addCDATA(map.get(XmlKeyEnum.FromUserName.getName()));
        xmlElement.addElement(XmlKeyEnum.FromUserName.getName()).addCDATA(map.get(XmlKeyEnum.ToUserName.getName()));
        xmlElement.addElement(XmlKeyEnum.CreateTime.getName()).addText(map.get(XmlKeyEnum.CreateTime.getName()));
        xmlElement.addElement(XmlKeyEnum.MsgType.getName()).addCDATA(map.get(XmlKeyEnum.MsgType.getName()));
        return xmlElement;
    }

    /**
     * @param map:
     * @author: 朱伟伟
     * @date: 2020-11-07 20:28
     * @description: 生成普通文本消息
     **/
    public static String generateReturnTextData(Map<String, String> map) {
        Element xmlElement = generateXmlElement(map);
        xmlElement.addElement("Content").addCDATA(map.get(XmlKeyEnum.Content.getName()));
        return xmlElement.asXML();
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
            return null;
        }
        Element xmlElement = document.getRootElement();
        Map<String, String> map = new LinkedHashMap<>(16);
        List<Element> elements = xmlElement.elements();
        for (Element element : elements) {
            map.put(element.getName(), element.getStringValue());
        }
        return map;
    }

}
