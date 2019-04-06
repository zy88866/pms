package com.fzy.pms.utils;

import com.fzy.pms.entity.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @program: JwtTokenUtil
 * @description: jwt 工具类
 * @author: fzy
 * @date: 2019/03/23 10:41:23
 **/
@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 密钥
     */
    private static final String SECRET="psm_token";

    public String generateToken(User user) {
        return generateToken(user.getUsername());
    }

    /**
     * 生成 Token
     * @param username 数据
     * @return 令牌
     */
    private String generateToken(String username){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+expiration * 1000))
                .setIssuedAt(new Date(System.currentTimeMillis())) //签发时间
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    /**
     * 查询用户名
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        Claims claims = this.parseToken(token);
        if(!Objects.isNull(claims)){
            return claims.getSubject();
        }
        return null;
    }


    /**
     * 解析 Token
     * @param token
     * @return
     */
    private Claims parseToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = parseToken(token);
        if(!Objects.isNull(claims)){
            return this.generateToken(claims.getSubject());
        }
        return null;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        if(!Objects.isNull(claims)){
           return claims.getExpiration().before(new Date());
        }
        return true;
    }

}
