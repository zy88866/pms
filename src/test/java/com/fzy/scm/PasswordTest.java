package com.fzy.scm;

import com.fzy.scm.dao.PrivilegeRepository;
import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.Privilege;
import com.fzy.scm.entity.Role;
import com.fzy.scm.entity.User;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
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

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    public void encodeTest(){
        System.out.println(passwordEncoder.encode("admin"));
    }

    @Test
    //@Transactional
    public void addUser(){

        Role role=new Role();
        role.setName("管理员1");
        role.setDeleteFlag(0);
        role.setCreateTime(new Date());
        role.setCreateUser("admin");

        User user=new User();
        user.setUserName("admin1");
        user.setPassword(passwordEncoder.encode("admin1"));
        user.setRealName("名字不能为空");
        user.setCreateTime(new Date());
        user.setCreateUser("admin");
        user.setDeleteFlag(0);
        user.setRoles(Sets.newHashSet(role));
        userRepository.save(user);

        Privilege privilege=new Privilege();
        privilege.setName("费用设置");
        privilege.setDeleteFlag(0);
        privilege.setCreateTime(new Date());
        privilege.setCreateUser("admin");
        privilege.setRoles(Collections.singletonList(role));
        privilegeRepository.save(privilege);
    }

    @Test
    public void findUser(){
        userRepository.findByUserName("admin").ifPresent(System.out::println);
    }
 }
