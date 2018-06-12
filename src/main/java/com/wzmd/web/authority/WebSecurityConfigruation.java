package com.wzmd.web.authority;

import com.wzmd.web.filter.AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 12:33 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Configuration      // 声明为配置类
@EnableWebSecurity      // 启用 Spring Security web 安全的功能
public class WebSecurityConfigruation extends WebSecurityConfigurerAdapter {

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

//    @Bean
//    UserDetailsService customUserService(){
//        return new CustomUserService();
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/").permitAll()
                .antMatchers("/admin").authenticated()       // 需携带有效 token
                .antMatchers("/admin").hasAuthority("admin")   // 需拥有 admin 这个权限
                .antMatchers("/ADMIN").hasRole("ADMIN")     // 需拥有 ADMIN 这个身份
                .anyRequest().permitAll()       // 允许所有请求通过
                .and()
                .csrf()
                .disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement()                        // 定制我们自己的 session 策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
