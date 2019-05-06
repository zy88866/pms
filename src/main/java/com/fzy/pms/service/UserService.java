package com.fzy.pms.service;

import com.fzy.pms.entity.dto.UserDto;
import com.fzy.pms.entity.security.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * @program: UserService
 * @description:
 * @author: fzy
 * @date: 2019-04-05 15:23
 **/
public interface UserService extends UserDetailsService {

    /**
     * 查询当前用户信息
     *
     * @return
     */
    Optional<UserDto> getCurrUserInfo();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * 锁定用户
     *
     * @param id
     */
    void lockUser(Long id);

    /**
     * 根据用户名查询用户
     *
     * @param Username
     * @return
     */
    Optional<User> getUserByUsername(String Username);

    /**
     * 查询全部用户列表
     *
     * @return
     */
    List<UserDto> findAllListSortCreateTime();

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Boolean updateUserInfo(User user);

    /**
     * 模糊搜索
     * @param user
     * @return
     */
    List<UserDto> search(User user);

    /**
     * 通过id 查询用户
     * @param id
     * @return
     */
    Optional<UserDto> findUser(Long id);
}