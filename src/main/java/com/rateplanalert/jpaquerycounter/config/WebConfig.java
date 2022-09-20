package com.rateplanalert.jpaquerycounter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rateplanalert.jpaquerycounter.QueryCountInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final QueryCountInterceptor queryCountInterceptor;

    public WebConfig(final QueryCountInterceptor queryCountInterceptor) {
        this.queryCountInterceptor = queryCountInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(queryCountInterceptor)
            .addPathPatterns("/**");
    }
}
