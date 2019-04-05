package com.fzy.pms.web.config;

import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.utils.ResponseUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: FuryAuthenticationFailureHandler
 * @description: 登陆失败拦截器
 * @author: fzy
 * @date: 2019/03/23 20:58:31
 **/
@Component
public class FuryAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            ResponseUtil.out(response, Result.failure("用户名或密码错误"));
        }else if(e instanceof DisabledException) {
            ResponseUtil.out(response, Result.failure("账户被禁用，请联系管理员"));
        }else {
            ResponseUtil.out(response, Result.failure("登录失败"));
        }
    }
}
