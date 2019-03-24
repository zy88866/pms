package com.fzy.scm.web.config;

import com.fzy.scm.entity.rest.Result;
import com.fzy.scm.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: MacLoginUrlAuthenticationEntryPoint
 * @description:
 * @author: fzy
 * @date: 2019/03/25 00:25:30
 **/
public class MacLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public MacLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response,Result.failure("非法访问,请先登陆!!!"));
    }
}
