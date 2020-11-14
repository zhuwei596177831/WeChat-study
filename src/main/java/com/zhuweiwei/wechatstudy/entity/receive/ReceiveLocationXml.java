package com.zhuweiwei.wechatstudy.entity.receive;

import com.zhuweiwei.wechatstudy.entity.BaseXml;

/**
 * @author 朱伟伟
 * @date 2020-11-13 19:01:09
 * @description 接收地理位置数据
 */
public class ReceiveLocationXml extends BaseXml {
    private static final long serialVersionUID = -7942531221735712391L;
    /**
     * 地理位置纬度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;
    /**
     * 消息id，64位整型
     */
    private String MsgId;

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
