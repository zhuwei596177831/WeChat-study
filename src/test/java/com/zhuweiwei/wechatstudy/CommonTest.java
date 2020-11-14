package com.zhuweiwei.wechatstudy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 朱伟伟
 * @date 2020-11-13 19:27:08
 * @description
 */
public class CommonTest {
    public static void main(String[] args) throws JsonProcessingException {
        String msg = "{\"data\":\"{\"name\":\"zww\"}\"}";
//        String msg = "{\"data\":{\"name\":\"zww\"}}";
//        String msg = "{\"data\":{\\\"name\\\":\\\"zww\\\"}}";
//        msg = msg.replace("\\", "");
        JSONObject jsonObject = JSON.parseObject(msg);
        System.out.println(jsonObject);
    }
}

class Data {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
