package com.wzmd.web.dao;

import com.wzmd.web.authority.LoginDetail;
import com.wzmd.web.authority.UserDetailImpl;
import com.wzmd.web.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 18:59 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 */
public interface LoginMapper {
   /* SELECT
    `user`.username,
            `user`.`password`,
            `user`.role_id,
            `user`.enable,
            `user`.last_password_change,
            `user`.enable,
    role.auth
            FROM
    `user` ,
    role
            WHERE
    `user`.role_id = role.role_id AND
    `user`.username = #{username}*/
    @Select("SELECT u.username,u.password,u.role_id,u.enable,u.last_password_change,r.auth FROM user u join role r on u.role_id = r.role_id where u.username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT u.username,u.password,u.role_id,u.enable,u.last_password_change,r.auth FROM user u join role r on u.role_id = r.role_id where u.username = #{username}")
    User findByUsername1(@Param("username")String username);

    @Select("SELECT u.username,u.password,u.role_id,u.enable,u.last_password_change,r.auth FROM user u join role r on u.role_id = r.role_id where u.username = #{username} and u.password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
