package com.wzmd.web.entity;

import com.wzmd.web.authority.LoginDetail;
import com.wzmd.web.authority.TokenDetail;

/**
 * Created by 马东 on 2018/3/29.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 21:02 2018/3/29
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class User implements LoginDetail, TokenDetail {

    private String username;
    private String password;
    private String auth;
    private Long last_password_change;
    private char enable;

    public User() {
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Long getLast_password_change() {
        return last_password_change;
    }

    public void setLast_password_change(Long last_password_change) {
        this.last_password_change = last_password_change;
    }

    public char getEnable() {
        return enable;
    }

    public User setEnable(char enable) {
        this.enable = enable;
        return this;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean enable() {
        if (this.enable == '1'){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities='" + auth + '\'' +
                ", lastPasswordChange=" + last_password_change +
                ", enable=" + enable +
                '}';
    }
}