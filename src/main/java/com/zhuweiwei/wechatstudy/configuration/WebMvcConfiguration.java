package com.zhuweiwei.wechatstudy.configuration;

import com.zhuweiwei.wechatstudy.interceptor.WeChatSignatureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 朱伟伟
 * @date 2020-11-07 16:47:12
 * @description mvc自定义配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    WeChatSignatureInterceptor weChatSignatureInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(weChatSignatureInterceptor).addPathPatterns("/**");
    }
}
