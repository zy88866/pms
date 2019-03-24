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
        User user = jwtTokenUtil.
                parseUserToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6MiwidXNlcm5hbWUiOiJhZG1pbiIsInJlYWxOYW1lIjoi5byg5LiJIiwiZXhwIjoxNTUzMzk2MTgwfQ.mpmY5u2mOOcsELQlrcNgN8_rPv4S7Ahc_lCg-IPYuqEh-9KERehM4d5QqnsIQqDpHGWTnpKfM9p7EKPGnf6zHA");
        System.out.println(user);

    }

}
