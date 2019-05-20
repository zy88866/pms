package com.fzy.pms;

import com.fzy.pms.dao.DoorRepository;
import com.fzy.pms.entity.dto.DoorDto;
import com.fzy.pms.entity.pms.Door;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @program: DoorTest
 * @description:
 * @author: fzy
 * @date: 2019/05/20 15:11:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DoorTest {

    @Autowired
    private DoorRepository doorRepository;

    @Test
    public void findOne(){
        DoorDto oneById = doorRepository.findOneById(1L);
        System.out.println(oneById);
    }
}
