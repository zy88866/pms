package com.fzy.scm.web.config;

import com.fzy.scm.entity.enums.RestCode;
import com.fzy.scm.entity.rest.Result;
import com.fzy.scm.entity.security.User;
import com.fzy.scm.utils.JwtTokenUtil;
import com.fzy.scm.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
public class JwtTokenFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        //判断Token 是否过期
        if(StringUtils.isEmpty(token) || jwtTokenUtil.isTokenExpired(token)) {
            ResponseUtil.out(response,Result.failure(RestCode.TOKEN_EXPIRE));
        }
        try {
            User user = jwtTokenUtil.parseUserToken(token);
            List<GrantedAuthority> authorities = new ArrayList<>();
            UsernamePasswordAuthenticationToken userToken=new
                    UsernamePasswordAuthenticationToken(user,null,authorities);
            SecurityContextHolder.getContext().setAuthentication(userToken);
        }catch (Exception e){
            ResponseUtil.out(response,Result.failure("token 解析出错"));
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求头重获取token
     * @param request
     * @return
     */
    private static String getToken(HttpServletRequest request){
        String header = request.getParameter(TOKEN_HEADER);
        if(StringUtils.isNotBlank(header)){
            String requestHeader = request.getHeader(TOKEN_HEADER);
            if(StringUtils.isNotBlank(requestHeader) && requestHeader.startsWith(TOKEN_PREFIX)){
                return requestHeader.substring(TOKEN_HEADER.length());
            }
        }
        return null;
    }
}
