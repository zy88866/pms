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

    /** 依赖注入自定义的登录失败处理器 */
    @Autowired
    private FuryAuthenticationFailureHandler furyAuthenticationFailureHandler;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

    @Override //配置策略
    protected void configure(HttpSecurity http) throws Exception {
        /*super.configure(http);
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()    .anyRequest().authenticated().and()
                .httpBasic()
                .and().formLogin().defaultSuccessUrl("/index")
                .and().logout().logoutSuccessUrl("/login");*/

            http.formLogin()
                .loginProcessingUrl("/login")
                //成功处理类
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
                .addFilter(new JwtTokenFilter(authenticationManager()))
                //关闭跨站请求防护
                .csrf().disable();
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

    @Bean //密码加密
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
