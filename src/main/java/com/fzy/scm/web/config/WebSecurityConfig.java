package com.fzy.scm.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

    @Override //配置策略
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/static/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().formLogin().defaultSuccessUrl("/index");
        super.configure(http);
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
       // auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123456")).authorities("ROLE_USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Bean //密码加密
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
