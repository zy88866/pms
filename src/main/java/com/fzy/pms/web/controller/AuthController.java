package com.fzy.pms.web.controller;

import com.fzy.pms.cache.TokenCache;
import com.fzy.pms.entity.enums.RestCode;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.entity.security.JwtToken;
import com.fzy.pms.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @program: LogoutController
 * @description:
 * @author: fzy
 * @date: 2019-04-20 19:40
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TokenCache tokenCache;

    @PostMapping("/token")
    public Result refreshToken(@RequestBody JwtToken jwtToken){
        JwtToken token = jwtTokenUtil.refreshToken(jwtToken);
        if(Objects.isNull(token)){
            return Result.failure(RestCode.TOKEN_EXPIRE);
        }
        tokenCache.add(token);
        return Result.success(token);
    }
}
