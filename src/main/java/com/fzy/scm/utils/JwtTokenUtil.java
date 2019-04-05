package com.fzy.scm.utils;

import com.fzy.scm.entity.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @program: JwtTokenUtil
 * @description:
 * @author: fzy
 * @date: 2019/03/23 10:41:23
 **/
@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 密钥
     */
    private static final String SECRET="scm_token";

    private static final String CLAIM_KEY_USERNAME = "username";

    /**
     * 生成 Token
     * @param user
     * @return
     */
    public String generateToken(User user){
        Claims map= new DefaultClaims();
        map.put(CLAIM_KEY_USERNAME,user.getUsername());
        return generateToken(map);
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public User parseUserToken(String token){
        Claims claims = parseToken(token);
        User user=new User();
        user.setUsername((String) claims.get(CLAIM_KEY_USERNAME));
        return user;
    }

    /**
     * 生成 Token
     * @param claims 数据
     * @return 令牌
     */
    public String generateToken(Claims claims){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expiration * 1000))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    /**
     * 解析 Token
     * @param token
     * @return
     */
    public Claims parseToken(String token){
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
        claims.setExpiration(new Date(System.currentTimeMillis()+expiration * 1000));
        return generateToken(claims);
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
