package com.fzy.pms.service.impl;

import com.fzy.pms.dao.RepairsRepository;
import com.fzy.pms.entity.enums.RepairsStatus;
import com.fzy.pms.entity.pms.Repairs;
import com.fzy.pms.service.RepairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: RepairsServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/22 12:09:25
 **/
@Service
public class RepairsServiceImpl implements RepairsService {

    @Autowired
    private RepairsRepository repairsRepository;

    @Override
    public void create(Repairs repairs) {
        repairs.setRepairsStatus(RepairsStatus.NOT);
        repairs.setRepairsDate(new Date());
        repairs.setRepairsBillNo("BX"+System.currentTimeMillis());
        repairsRepository.save(repairs);
    }
}
