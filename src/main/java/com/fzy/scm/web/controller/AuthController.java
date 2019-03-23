package com.fzy.scm.web.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: AuthController
 * @description:
 * @author: fzy
 * @date: 2019/03/23 16:02:58
 **/
@RestController
@RequestMapping("/auth")
public class AuthController  {

    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;


}
