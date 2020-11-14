package com.zhuweiwei.wechatstudy.util;

import com.thoughtworks.xstream.XStream;
import com.zhuweiwei.wechatstudy.entity.BaseXml;
import com.zhuweiwei.wechatstudy.entity.response.BaseImage;
import com.zhuweiwei.wechatstudy.entity.XmlData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 朱伟伟
 * @date 2020-11-11 21:56:42
 * @description
 */
public class XStreamUtil {
    private static final Logger logger = LoggerFactory.getLogger(XStreamUtil.class);
    private static final XStream XML;

    static {
        XML = new XStream();
        XML.alias("xml", XmlData.class);
        XML.alias("Image", BaseImage.class);
        //Security framework of XStream not initialized, XStream is probably vulnerabl
        XML.allowTypesByRegExp(new String[]{".*"});
    }

    public static String toXml(XmlData xmlData) {
        return XML.toXML(xmlData);
    }

    public static XStream getXStream(Class<? extends BaseXml> aClass) {
        XStream xStream = new XStream();
        xStream.alias("xml", aClass);
        return xStream;
    }


    public static XmlData parseDataFromXml(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            StringBuilder stringBuilder = new StringBuilder(256);
            inputStreamReader = new InputStreamReader(inputStream);
            char[] chars = new char[1024];
            while (inputStreamReader.read(chars) != -1) {
                stringBuilder.append(chars);
            }
            String s = stringBuilder.toString();
            logger.info("请求报文：\n{}", s);
            return (XmlData) XML.fromXML(s);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("解析xml报文失败：{}", e.getMessage());
            return new XmlData();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
