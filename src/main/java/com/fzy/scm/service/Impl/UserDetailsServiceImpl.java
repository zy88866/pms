package com.fzy.scm.service.Impl;

import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @program: UserDetailsServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/03/16 18:46:52
 **/

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseGet(()-> {
            throw new UsernameNotFoundException("用户名不存在");
        });
    }

    /**
     * 查询当前用户信息
     * @return
     */
    public Optional<User> getCurrUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.isNull(authentication)? Optional.empty(): Optional.of((User)authentication.getPrincipal());
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    public User registerUser(User user){
        //密码加密
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }



}
