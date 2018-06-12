package com.wzmd.web.authority;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 14:10 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class TokenDetailImpl implements TokenDetail {
    private final String username;
    public TokenDetailImpl(String username){
        this.username = username;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
}
