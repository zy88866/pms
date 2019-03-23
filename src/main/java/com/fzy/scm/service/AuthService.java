package com.fzy.scm.service;

import com.fzy.scm.entity.security.User;

/**
 * @program: AuthService
 * @description:
 * @author: fzy
 * @date: 2019/03/23 18:04:27
 **/
public interface AuthService {

    String login(User user);
}
