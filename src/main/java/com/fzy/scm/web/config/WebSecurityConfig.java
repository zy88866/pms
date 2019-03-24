package com.fzy.scm.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @program: WebSecurityConfig
 * @description:
 * @author: fzy
 * @date: 2019/03/16 18:15:36
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private FuryAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private FuryAuthenticationFailureHandler furyAuthenticationFailureHandler;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private  JwtTokenFilter jwtTokenFilter;

    @Override //配置策略
    protected void configure(HttpSecurity http) throws Exception {
            http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(furyAuthenticationFailureHandler)
                .and().authorizeRequests()
                .antMatchers("/login").permitAll() // 登录请求路径不进行过滤
                .anyRequest()
                .authenticated()
                .and()
                //前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //自定义权限拒绝处理类
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                //添加JWT filter
                .addFilterBefore(jwtTokenFilter, BasicAuthenticationFilter.class)
                //关闭跨站请求防护
                .csrf().disable();
                // 禁用缓存
                http.headers().cacheControl();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //每一个请求对应一个空的filter链,这里一般不要配置过多,
        web.ignoring().antMatchers("/static/**",
                "/v2/api-docs",
                "/configuration/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/swagge‌​r-ui.html");
    }

    //密码加密
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
