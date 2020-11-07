package com.zhuweiwei.wechatstudy;

import com.zhuweiwei.wechatstudy.properties.WeChatProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WechatStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatStudyApplication.class, args);
    }

}
