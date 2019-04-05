package com.fzy.pms.web.config;

import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: RestAccessDeniedHandler
 * @description: 权限拦截器
 * @author: fzy
 * @date: 2019/03/23 21:12:00
 **/
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.out(response, Result.failure("该用户没有权限"));
    }
}
