package com.fzy.scm.web.config;

import com.fzy.scm.entity.rest.Result;
import com.fzy.scm.entity.security.User;
import com.fzy.scm.utils.JwtTokenUtil;
import com.fzy.scm.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: AuthenticationSuccessHandler
 * @description: 登陆成功拦截器
 * @author: fzy
 * @date: 2019/03/23 20:05:56
 **/
@Component
public class FuryAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwtTokenUtil.generateToken(principal);
        ResponseUtil.out(response, Result.success(token));
    }
}
