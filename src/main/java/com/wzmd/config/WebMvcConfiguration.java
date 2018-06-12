package com.wzmd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 12:30 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
    }
}
