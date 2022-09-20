package com.rateplanalert.jpaquerycounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.rateplanalert.jpaquerycounter.JpaInspector;
import com.rateplanalert.jpaquerycounter.QueryCountInterceptor;

@Configuration
@PropertySource(
    value = "classpath:application-jpaquerycounter.properties"
)
public class SpringConfig {

    @Bean
    public QueryCountInterceptor queryCountInterceptor() {
        return new QueryCountInterceptor(jpaInspector());
    }

    @Bean
    public JpaInspector jpaInspector() {
        return new JpaInspector();
    }
}
