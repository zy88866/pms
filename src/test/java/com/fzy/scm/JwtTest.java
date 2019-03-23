package com.fzy.scm;

import com.fzy.scm.entity.security.User;
import com.fzy.scm.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: JwtTest
 * @description: jwt toekn 测试
 * @author: fzy
 * @date: 2019/03/23 11:32:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JwtTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void createJwtToken(){
        User user=new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setRealName("张三");
        log.info("token={}",jwtTokenUtil.generateToken(user));
    }

    @Test
    public void paramJwtToken(){
        User user = jwtTokenUtil.parseUserToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsInJlYWxOYW1lIjoi5byg5LiJIiwiZXhwIjoxNTUzMzYzMzQzfQ.Y3XWx5-oPZPofIrzQZep6ytw86RiAPFH9KCXPvWJCxs4aSe4NGUqUeDvgPc7_oMdEL5lv5YkJkPez9frWAKz9A");
        System.out.println(user);

    }

}
