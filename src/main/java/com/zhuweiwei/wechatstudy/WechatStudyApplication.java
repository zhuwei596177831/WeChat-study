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
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@SpringBootApplication
@EnableScheduling
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
//        deleteMenus();
    }

    private void deleteMenus() {
        restTemplateBuilder.build().getForObject(UploadUrl.DELETE_MENU.getUrl() + RefreshAccessToken.getAccessToken(), String.class);
    }

    private void addMenus() {
        JSONObject data = new JSONObject();
        JSONArray button = new JSONArray();

        JSONObject clickMeNow = new JSONObject();
        clickMeNow.put("type", "click");
        clickMeNow.put("name", "快点我啊");
        clickMeNow.put("key", "clickMeNow");
        JSONArray sub_button = new JSONArray();
        JSONObject ypp = new JSONObject();
        ypp.put("type", "click");
        ypp.put("name", "闫盼盼");
        ypp.put("key", "ypp");
        sub_button.add(ypp);
        JSONObject TODAY_MOVIE = new JSONObject();
        TODAY_MOVIE.put("type", "click");
        TODAY_MOVIE.put("name", "今日影片");
        TODAY_MOVIE.put("key", "todayMovie");
        sub_button.add(TODAY_MOVIE);
        clickMeNow.put("sub_button", sub_button);
        button.add(clickMeNow);

        JSONObject historyPicture = new JSONObject();
        historyPicture.put("type", "view");
        historyPicture.put("name", "时光网");
        historyPicture.put("url", "http://www.mtime.com");
        button.add(historyPicture);

        JSONObject contactAuthor = new JSONObject();
        contactAuthor.put("type", "click");
        contactAuthor.put("name", "联系作者");
        contactAuthor.put("key", "contactAuthor");
        sub_button = new JSONArray();
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
