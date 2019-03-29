package com.fzy.scm;

import com.fzy.scm.dao.CostRepository;
import com.fzy.scm.entity.Cost;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @program: RedisTest
 * @description:
 * @author: fzy
 * @date: 2019/03/29 21:07:46
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private CostRepository costRepository;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        Cost cost=new Cost(1l,"物业费", Lists.newArrayList());
       // redisTemplate.opsForValue().set("aa",cost);
        costRepository.save(cost);
    }

    @Test
    public void test1(){
        System.out.println(costRepository.findAll());
      //  System.out.println(redisTemplate.opsForValue().get("aa"));
    }


}
