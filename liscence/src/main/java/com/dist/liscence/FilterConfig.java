
package com.dist.liscence;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * @company: DIST
 * @date：2017/5/17
 * @author: ChenYanping
 * desc
 */

@Configuration
public class FilterConfig {

    /**
     * 针对应用系统系统的过滤规则
     * todo 方便调试，暂时取消拦截
     * @return
     */
//    @Bean
//    public FilterRegistrationBean appFilterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        AppValidateFilter filter = new AppValidateFilter();
//        registrationBean.setFilter(filter);
//        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/rest/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        registrationBean.setOrder(Integer.MAX_VALUE);
//        return registrationBean;
//    }


    @Bean
    public FilterRegistrationBean liscence(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        RegisterFilter filter = new RegisterFilter();
        registrationBean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/test/*");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;

    }


}
