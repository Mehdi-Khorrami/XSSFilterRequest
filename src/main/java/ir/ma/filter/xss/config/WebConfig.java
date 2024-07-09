package ir.ma.filter.xss.config;


import ir.ma.filter.xss.service.XSSRequestWrapperStrategyService;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<XSSFilter> filterRegistrationBean(XSSRequestWrapperStrategyService strategyService) {
        FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XSSFilter(strategyService));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
