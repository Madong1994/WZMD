package com.wzmd.web.authority;

import com.wzmd.web.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Date;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 14:25 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public class SecurityModelFactory {
    public static UserDetailImpl create(User user){
        Collection<? extends GrantedAuthority> authorities;
        try{
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuth());
        }catch (Exception e){
            authorities = null;
        }
        Date lastPasswordReset = new Date();
        lastPasswordReset.setTime(user.getLast_password_change());
        return new UserDetailImpl(
                user.getUsername(),
                user.getUsername(),
                user.getPassword(),
                lastPasswordReset,
                authorities,
                user.enable()
        );
    }
}
