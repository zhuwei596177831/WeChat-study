package com.zhuweiwei.wechatstudy.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * @author 朱伟伟
 * @date 2020-11-07 18:24:54
 * @description
 */
public class XStreamUtil {

    //xstream扩展
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    public static String object2Xml(Object obj, Object child, String alias, String aliasForChild) {
        xstream.alias(alias, obj.getClass());
        xstream.alias(aliasForChild, child.getClass());
        String xml = xstream.toXML(obj);
        return xml;
    }

    public static String object2Xml(Object obj, String alias) {
        xstream.alias(alias, obj.getClass());
        String xml = xstream.toXML(obj);
        return xml;
    }
}
