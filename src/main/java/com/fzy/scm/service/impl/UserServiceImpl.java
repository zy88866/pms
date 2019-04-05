package com.fzy.scm.service.impl;

import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.dto.UserDto;
import com.fzy.scm.entity.mapper.UserMapper;
import com.fzy.scm.entity.security.User;
import com.fzy.scm.exception.SystemErrorException;
import com.fzy.scm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @program: UserServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/03/16 18:46:52
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseGet(()-> {
            throw new UsernameNotFoundException("用户名不存在");
        });
    }

    @Override
    public Optional<UserDto> getCurrUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!Objects.isNull(authentication)){
            return Optional.ofNullable(userMapper.toDto((User) authentication.getPrincipal()));
        }
        return Optional.empty();
    }

    @Override
    public User registerUser(User user){
        //密码加密
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       if (userRepository.findByUsername(user.getUsername()).isPresent()){
           throw new SystemErrorException("用户名已存在");
       }
       return userRepository.save(user);
    }

    @Override
    public void lockUser(Long id){
        userRepository.lockUser(id);
    }

    @Override
    public Optional<User> getUserByUsername(String Username){
        if (StringUtils.isNotBlank(Username)){
           return userRepository.findByUsername(Username);
        }
      return Optional.empty();
    }
}
