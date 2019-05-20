package com.fzy.pms.service.impl;

import com.fzy.pms.dao.ParkRepository;
import com.fzy.pms.entity.pms.Park;
import com.fzy.pms.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: ParkServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/20 19:05:12
 **/
@Service
public class ParkServiceImpl implements ParkService {

    @Autowired
    private ParkRepository parkRepository;

    @Override
    public void create(Park park) {

    }
}
