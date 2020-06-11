package com.luo.diamonds.config;

import com.luo.diamonds.interceptor.AjaxDomainInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private AjaxDomainInterceptor ajaxDomainInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//         自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(ajaxDomainInterceptor).addPathPatterns("/**");

    }
}
