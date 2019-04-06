package com.fzy.pms;

import com.fzy.pms.entity.security.User;
import com.fzy.pms.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: JwtTokenTest
 * @description: jwt toekn 测试
 * @author: fzy
 * @date: 2019/03/23 11:32:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JwtTokenTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void createJwtToken(){
        User user=new User();
        user.setUsername("admin");
        log.info("token={}",jwtTokenUtil.generateToken(user));
    }

    @Test
    public void paramJwtToken(){
      String username = jwtTokenUtil.getUsernameFromToken
                ("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NDU0NzMxMSwiaWF0IjoxNTU0NTQzNzExfQ.yr8RBtq1EC-YJQj7z4bJV8I92Tsui4aEg7LvYrH_CDxupPjw1ScrbKC1iD8YHt5DLk5nhXG75-qH24WhkDgVQQ");
        System.out.println(username);

    }

}
