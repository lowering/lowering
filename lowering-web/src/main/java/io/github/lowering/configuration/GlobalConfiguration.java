package io.github.lowering.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * Created by Administrator on 2017/5/22.
 */
@Configuration
public class GlobalConfiguration {

    @Bean
    public FilterRegistrationBean openEntityManagerInViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new OpenEntityManagerInViewFilter());
        registrationBean.setOrder(5);
        return registrationBean;
    }
}
