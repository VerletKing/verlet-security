package org.verlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.verlet.filter.TimeFilter;
import org.verlet.filter.TimeInterceptor;

/**
 * @author verlet
 * @date 2018/2/17
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns("/**").excludePathPatterns("/testException");
        super.addInterceptors(registry);
    }

    /**
     * 加入第三方的filter
     */
//    @Bean
//    public FilterRegistrationBean registrationBean() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new TimeFilter());
//        registration.addUrlPatterns("/*");
//        return registration;
//    }
}
