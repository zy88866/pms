package com.fzy.pms.utils;

import com.fzy.pms.entity.security.JwtToken;
import com.fzy.pms.entity.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
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
    private int expiration;

    @Value("${jwt.time-out}")
    private int timeOUt;

    @Value("${jwt.secret}")
    private String SECRET;

    public JwtToken generateToken(User user) {
        return generateToken(user.getUsername());
    }

    /**
     * 生成 Token
     * @param username 数据
     * @return 令牌
     */
    private JwtToken generateToken(String username){

        Calendar instance = Calendar.getInstance();

        instance.add(Calendar.MINUTE,expiration);

        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setExpiration(instance.getTime())
                .setIssuedAt(new Date(System.currentTimeMillis())) //签发时间
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        instance.add(Calendar.MINUTE,timeOUt);
        String refreshToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setExpiration(instance.getTime())
                .setIssuedAt(new Date(System.currentTimeMillis())) //签发时间
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return new JwtToken().setAccessToken(accessToken).setRefreshToken(refreshToken);
    }

    /**
     * 查询用户名
     * @param jwtToken
     * @return
     */
    public String getUsernameFromToken(JwtToken jwtToken){
        Claims claims = this.parseToken(jwtToken.getAccessToken());
        if(!Objects.isNull(claims)){
            return claims.getSubject();
        }
        return null;
    }

    /**
     * 解析Token
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
    public JwtToken refreshToken(JwtToken token){
        Claims claims = parseToken(token.getRefreshToken());
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
