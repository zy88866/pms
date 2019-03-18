package com.fzy.scm.web.controller;

import com.fzy.scm.entity.security.User;
import com.fzy.scm.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @program: HomeController
 * @description:
 * @author: fzy
 * @date: 2019/03/17 17:20:57
 **/

@Controller
public class HomeController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/index")
    public String index(Model model){
        User user = userDetailsService.getUserDetails();
        return "index";
    }

}
