package com.zhuweiwei.wechatstudy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuweiwei.wechatstudy.component.RefreshAccessToken;
import com.zhuweiwei.wechatstudy.constant.UploadUrl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.annotation.Resource;

@SpringBootApplication
public class WechatStudyApplication implements CommandLineRunner {
    @Resource
    RestTemplateBuilder restTemplateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(WechatStudyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Thread.sleep(8000);
//        addMenus();
    }

    private void addMenus() {
        JSONObject data = new JSONObject();
        JSONArray button = new JSONArray();

        JSONObject featuredPicture = new JSONObject();
        featuredPicture.put("type", "click");
        featuredPicture.put("name", "精选图片");
        featuredPicture.put("key", "featuredPicture");
        button.add(featuredPicture);

        JSONObject historyPicture = new JSONObject();
        historyPicture.put("type", "view");
        historyPicture.put("name", "时光网");
        historyPicture.put("url", "http://www.mtime.com");
        button.add(historyPicture);

        JSONObject contactAuthor = new JSONObject();
        contactAuthor.put("type", "click");
        contactAuthor.put("name", "联系作者");
        contactAuthor.put("key", "contactAuthor");
        JSONArray sub_button = new JSONArray();
        JSONObject weChat = new JSONObject();
        weChat.put("type", "click");
        weChat.put("name", "微信");
        weChat.put("key", "weChat");
        sub_button.add(weChat);
        JSONObject aliPay = new JSONObject();
        aliPay.put("type", "click");
        aliPay.put("name", "支付宝");
        aliPay.put("key", "aliPay");
        sub_button.add(aliPay);
        JSONObject phone = new JSONObject();
        phone.put("type", "click");
        phone.put("name", "手机号");
        phone.put("key", "phone");
        sub_button.add(phone);
        contactAuthor.put("sub_button", sub_button);
        button.add(contactAuthor);

        data.put("button", button);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(data.toJSONString(), httpHeaders);
        String url = UploadUrl.ADD_MENU.getUrl() + RefreshAccessToken.getAccessToken();
        String body = restTemplateBuilder.build().postForEntity(url,
                httpEntity, String.class).getBody();
        System.out.println(body);
    }

}
