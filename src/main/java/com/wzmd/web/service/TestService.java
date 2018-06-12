package com.wzmd.web.service;

import com.wzmd.web.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by 马东 on 2018/3/29.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:01 2018/3/29
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Service
public class TestService {
    @Cacheable(value="thisredis", key="'users_'+#id")
    public User findUser(Integer id) {
        User user = new User();
//        user.setUserName("hlhdidi");
//        user.setPassword("123");
//        user.setUid(id);
//        System.out.println("log4j2坏啦?");
//        System.out.println("输入user,用户名:{},密码:{}"+user.getUserName()+user.getPassword());
        return user;
    }

    @CacheEvict(value="thisredis", key="'users_'+#id",condition="#id==1")
    public void delUser(Integer id) {
        // 删除user
        System.out.println("user删除");
    }
}
