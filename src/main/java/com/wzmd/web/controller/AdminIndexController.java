package com.wzmd.web.controller;

import com.wzmd.web.BaseController;
import com.wzmd.web.entity.User;
import com.wzmd.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 马东 on 2018/3/22.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 0:58 2018/3/22
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {
    @Autowired
    TestService testService;

    @RequestMapping("/index")
    public String index(){
        return "/views/index";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        User user = testService.findUser(1);
        if(user != null){
//            System.out.println(user.getUserName());
        }

        return "/login";
    }
    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        testService.findUser(1);

        return "/login";
    }
    @RequestMapping("/test3")
    @ResponseBody
    public String test3(){
        testService.delUser(1);

        return "/login";
    }
}
