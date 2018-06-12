package com.wzmd.web.service.impl;

import com.wzmd.web.authority.TokenDetail;
import com.wzmd.web.authority.TokenUtils;
import com.wzmd.web.dao.LoginMapper;
import com.wzmd.web.entity.User;
import com.wzmd.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 18:57 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    private final TokenUtils tokenUtils;

    @Autowired
    public LoginServiceImpl(TokenUtils tokenUtils) {
//        this.loginRepository = loginRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public User getLoginDetail(String username) {
        return loginMapper.findByUsername(username);
    }

    @Override
    public User loginVerify(String username, String password) {
        return loginMapper.findByUsernameAndPassword(username,password);
    }

    @Override
    public String generateToken(TokenDetail tokenDetail) {
        return tokenUtils.generateToken(tokenDetail);
    }
}
