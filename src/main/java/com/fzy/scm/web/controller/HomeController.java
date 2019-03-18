package com.fzy.scm.web.controller;

import com.fzy.scm.entity.Privilege;
import com.fzy.scm.entity.Role;
import com.fzy.scm.entity.User;
import com.fzy.scm.service.UserDetailsServiceImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;


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
        Set<Privilege> set= Sets.newHashSet();
        for (Role role:user.getRoles()){
            set.addAll(role.getPrivileges());
        }
        model.addAttribute("privilegesList",set);
        return "index";
    }

}
