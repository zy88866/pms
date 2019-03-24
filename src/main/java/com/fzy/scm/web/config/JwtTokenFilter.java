package com.fzy.scm.web.config;

import com.fzy.scm.entity.enums.RestCode;
import com.fzy.scm.entity.rest.Result;
import com.fzy.scm.entity.security.User;
import com.fzy.scm.utils.JwtTokenUtil;
import com.fzy.scm.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: JwtAuthenticationTokenFilter
 * @description:
 * @author: fzy
 * @date: 2019/03/23 11:50:41
 **/
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        String token = getToken(req);
        //判断Token 是否过期
        if(StringUtils.isEmpty(token) || jwtTokenUtil.isTokenExpired(token)) {
            ResponseUtil.out(res,Result.failure(RestCode.TOKEN_EXPIRE));
            return;
        }
        User user = jwtTokenUtil.parseUserToken(token);
        List<GrantedAuthority> authorities = new ArrayList<>();
        UsernamePasswordAuthenticationToken userToken=new
                UsernamePasswordAuthenticationToken(user,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(userToken);
        chain.doFilter(req, res);
    }

    /**
     * 从请求头重获取token
     * @param request
     * @return
     */
    private static String getToken(HttpServletRequest request){
        String header = request.getHeader(TOKEN_HEADER);
        if(StringUtils.isNotBlank(header) && header.startsWith(TOKEN_PREFIX)){
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
