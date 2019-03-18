package com.fzy.scm.service;

import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: UserDetailsServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/03/16 18:46:52
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseGet(()-> {
            throw new UsernameNotFoundException("用户名不存在");
        });
    }

    public User getUserDetails(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        return (User) auth.getPrincipal();
    }

}
