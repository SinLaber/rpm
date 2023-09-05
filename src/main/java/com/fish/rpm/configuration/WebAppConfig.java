package com.fish.rpm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Resource
    private LogConfig logInterceptor;

    @Resource
    private SingleSignConfig ssoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ssoInterceptor).addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "logout",
                        "/demo/**",
                        "/api/**"

                );
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }
}
