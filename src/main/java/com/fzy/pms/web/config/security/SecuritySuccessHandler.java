package com.fzy.pms.web.config.security;

import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.entity.security.User;
import com.fzy.pms.utils.JwtTokenUtil;
import com.fzy.pms.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: AuthenticationSuccessHandler
 * @description: 登陆成功拦截器
 * @author: fzy
 * @date: 2019/03/23 20:05:56
 **/
@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwtTokenUtil.generateToken(principal);
        ResponseUtil.out(response, Result.success(token));
    }
}
