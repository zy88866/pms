package com.fzy.scm.service.Impl;

import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.security.User;
import com.fzy.scm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @program: AuthServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/03/23 18:05:29
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(User user) {


        Optional<User> User = userRepository.findByUsername(user.getUsername());


        return null;
    }
}

