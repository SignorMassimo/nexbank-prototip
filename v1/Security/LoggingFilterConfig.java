package com.zexson.signor_p.Security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class LoggingFilterConfig {

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilterConf(LoggingFilter filter) {
        FilterRegistrationBean<LoggingFilter> registration = new FilterRegistrationBean<>(filter);
        registration.addUrlPatterns("/users/*");
        registration.setOrder(1);
        return registration;
    }
}
