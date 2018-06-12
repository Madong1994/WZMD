package com.wzmd.web.controller;

import com.alibaba.fastjson.JSON;
import com.wzmd.web.authority.LoginDetail;
import com.wzmd.web.authority.TokenDetail;
import com.wzmd.web.entity.ResultMap;
import com.wzmd.web.entity.User;
import com.wzmd.web.entity.ov.Data;
import com.wzmd.web.entity.ov.RequestLoginUser;
import com.wzmd.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 18:49 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Value("${token.header}")
    private String tokenHeader;

    @RequestMapping("/")
    public String index(){
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletResponse response,@Valid RequestLoginUser requestLoginUser, BindingResult bindingResult, Model model){
// 检查有没有输入用户名密码和格式对不对
        if (bindingResult.hasErrors()){
            ResultMap resultMap = new ResultMap().fail("400").message("缺少参数或者参数格式不对").data("");
            String json = JSON.toJSONString(resultMap);
//            model.addAttribute("resultMap",resultMap);
            return json;
        }

        LoginDetail loginDetail = loginService.loginVerify(requestLoginUser.getUsername(),requestLoginUser.getPassword());
        ResultMap ifLoginFail = checkAccount(requestLoginUser, loginDetail);
        if (ifLoginFail != null){
            String json = JSON.toJSONString(ifLoginFail);
//            model.addAttribute("resultMap",ifLoginFail);
            return json;
//            return "aa";
        }
        ResultMap resultMap =  new ResultMap().success().message("").data(new Data().addObj(tokenHeader, loginService.generateToken((TokenDetail) loginDetail)));
//        return "ddd";
        String json = JSON.toJSONString(resultMap);
        String t = loginService.generateToken((TokenDetail) loginDetail);
        Cookie cookie = new Cookie("xAuthToken",t);
//        cookie.setHttpOnly(true);
        cookie.setMaxAge(3*60);
        cookie.setPath("/");
        response.addCookie(cookie);
//        model.addAttribute("resultMap",resultMap);
//        response.h();
        return json;
    }
    private ResultMap checkAccount(RequestLoginUser requestLoginUser, LoginDetail loginDetail){
        if (loginDetail == null){
            return new ResultMap().fail("434").message("账号不存在！").data("");
        }else {
            if (loginDetail.enable() == false){
                return new ResultMap().fail("452").message("账号在黑名单中").data("");
            }
            if (!loginDetail.getPassword().equals(requestLoginUser.getPassword())){
                return new ResultMap().fail("438").message("密码错误！").data("");
            }
        }
        return null;
    }
}
