package com.zhuweiwei.wechatstudy.configuration;

import com.zhuweiwei.wechatstudy.interceptor.SignatureInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 朱伟伟
 * @date 2020-11-07 16:47:12
 * @description mvc自定义配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SignatureInterceptor()).addPathPatterns("/**");
    }
}
