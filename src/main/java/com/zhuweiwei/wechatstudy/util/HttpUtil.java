package com.zhuweiwei.wechatstudy.util;

import com.zhuweiwei.wechatstudy.component.RefreshAccessToken;
import com.zhuweiwei.wechatstudy.constant.UploadUrl;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Random;

/**
 * @author 朱伟伟
 * @date 2020-11-08 15:39:01
 * @description
 */
public class HttpUtil {

    public static String fineName = "";

    public static String postForSystemFile(String url, RestTemplate restTemplate, String type) {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("access_token", RefreshAccessToken.getAccessToken());
        multiValueMap.add("type", type);
        try {
            File folder = new File("D:/闫盼盼《 甜蜜的俘虜 》");
            if (folder.exists()) {
                File[] files = folder.listFiles();
                if (files != null && files.length > 0) {
                    File file = files[new Random().nextInt(files.length)];
                    fineName = file.getName();
                    multiValueMap.add("media", new FileSystemResource(file));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
    }

}
