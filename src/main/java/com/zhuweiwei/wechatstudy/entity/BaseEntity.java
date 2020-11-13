package com.zhuweiwei.wechatstudy.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author 朱伟伟
 * @date 2020-11-13 18:36:03
 * @description
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -2578597579717005909L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
