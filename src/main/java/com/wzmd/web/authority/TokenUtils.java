package com.wzmd.web.authority;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 马东 on 2018/3/30.
 *
 * @Author:madong
 * @Description:
 * @Date:Create in 13:56 2018/3/30
 * 关关雎鸠，在河之洲，
 * 窈窕淑女，君子好逑。
 *
 *把用户名封装进下载的轮子的 token 的主体 claims 中，并在里面封装了当前时间（方便后面判断 token 是否在修改密码之前生成的）
 *再计算 token 过期的时间写入到 轮子的 token 中
 *对 轮子的 token 进行撒盐加密，生成一串字符串，即我们定制的 token

 */
@Component
public class TokenUtils {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration}")
    private Long expiration;

    /**
     * 根据TokenDetail生成Token
     * @param tokenDetail
     * @return
     */
    public String generateToken(TokenDetail tokenDetail){
        Map<String,Object> claims = new HashMap<>();
        claims.put("sub",tokenDetail.getUsername());
        claims.put("created",this.generateCurrentDate());
        return this.generateToken(claims);
    }

    /**
     * 根据 claims生成Token
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims){
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException ex) {
            //didn't want to have this method throw the exception, would rather log it and sign the token like it was before
            logger.warn(ex.getMessage());
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret)
                    .compact();
        }
    }

    /**
     * token过期时间
     * @return
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+this.expiration);
//        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前时间
     * @return
     */
    private Date generateCurrentDate(){
        return new Date(System.currentTimeMillis());
    }

    /**
     * 从token中拿到username
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    /**
     * 解析token的主体Claims
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret.getBytes("utf-8"))
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * 检查token是否处于有效期内
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        UserDetailImpl user = (UserDetailImpl )userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);
        return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)) && !(this.isCreatedBeforeLastPasswordReset(created,user.getLastPasswordReset())));
    }

    /**
     * 获得我们封装在token中的token创建时间
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token){
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        }catch (Exception e){
            created = null;
        }
        return created;
    }
    /**
     * 获得我们封装在 token 中的 token 过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
    /**
     * 检查当前时间是否在封装在 token 中的过期时间之后，若是，则判定为 token 过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }
    /**
     * 检查 token 是否是在最后一次修改密码之前创建的（账号修改密码之后之前生成的 token 即使没过期也判断为无效）
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
}
