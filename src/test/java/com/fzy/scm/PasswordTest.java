package com.fzy.scm;

import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.security.Role;
import com.fzy.scm.entity.security.User;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @program: PasswordTest
 * @description:
 * @author: fzy
 * @date: 2019/03/17 13:01:10
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encodeTest(){
        System.out.println(passwordEncoder.encode("admin"));
    }

    @Test
    //@Transactional
    public void addUser(){
        Role role=new Role();
        role.setName("管理员");
        role.setDeleteFlag(0);
        role.setCreateTime(new Date());

        User user=new User();
        user.setUserName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRealName("张三");
        user.setDeleteFlag(0);
        user.setRoles(Sets.newHashSet(role));
        userRepository.save(user);
    }

    @Test
    public void findUser(){
        userRepository.findByUserName("admin").ifPresent(System.out::println);
    }
 }
