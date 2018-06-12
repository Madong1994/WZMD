package com.wzmd.web.filter;

import com.wzmd.web.authority.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 14:41 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * json web token 在请求头的名字
     */
    @Value("${token.header}")
    private String tokenHeader;
    /**
     * 辅助操作 token工具类
     */
    @Autowired
    private TokenUtils tokenUtils;

    /**
     *
     * Spring Security 的核心操作服务类
     * 在当前类中将使用 UserDetailsService 来获取 userDetails 对象
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        Cookie[] cookies = httpServletRequest.getCookies();
        String authToken = null;
// httpServletRequest.getHeader(this.tokenHeader);;
        if(cookies != null){
            authToken = cookies[0].getValue();
        }

//        String ss = httpServletRequest.getHeader("user-Agent");
        String username = this.tokenUtils.getUsernameFromToken(authToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (this.tokenUtils.validateToken(authToken,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            if (!userDetails.isEnabled()){
                res.setCharacterEncoding("utf-8");
                res.setContentType("application/json;charset = UTF-8");
                res.getWriter().print("{\"code\":\"452\",\"data\":\"\",\"message\":\"账号处于黑名单\"}");
                return;
            }
        }
        chain.doFilter(req,res);
//        super.doFilter(req, res, chain);
    }
}
