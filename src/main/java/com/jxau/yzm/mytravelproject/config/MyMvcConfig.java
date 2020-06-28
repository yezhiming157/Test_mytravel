package com.jxau.yzm.mytravelproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    private InterceptorConfig interceptorConfig;

    private static final Logger log = LoggerFactory.getLogger(MyMvcConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //将本地资源路径映射
        registry.addResourceHandler("/picture/**").addResourceLocations("file:G:/IdeaProjects/mytravelproject/src/main/resources/static/picture/");
    }


    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(interceptorConfig).addPathPatterns()
        log.debug("---------拦截器------------------");
        registry.addInterceptor(interceptorConfig).addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/admin","/admin/reg/**")
                .excludePathPatterns("/error","/static/**").excludePathPatterns("G:/IdeaProjects/mytravelproject/src/main/resources/static/picture/**");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("/admin/login");
        registry.addViewController("/admin/main.html").setViewName("/admin/dashboard");
        registry.addViewController("/admin/reg").setViewName("/admin/register");
        registry.addViewController("/user/reg").setViewName("/user/register");
        log.debug("---------视图解析器------------------");
    }
}
