package com.wzmd.web.service;


import com.wzmd.web.authority.LoginDetail;
import com.wzmd.web.authority.TokenDetail;
import com.wzmd.web.entity.User;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2017/10/3 2:11
 */
public interface LoginService {

    User getLoginDetail(String username);

    User loginVerify(String username,String password);

    String generateToken(TokenDetail tokenDetail);

}
