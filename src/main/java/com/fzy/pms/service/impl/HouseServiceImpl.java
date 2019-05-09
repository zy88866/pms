package com.fzy.pms.service.impl;

import com.fzy.pms.dao.HouseRepository;
import com.fzy.pms.entity.pms.House;
import com.fzy.pms.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @program: HouseServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/09 13:38:02
 **/
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public Page<House> findAll(Pageable pageableDefault) {
        return null;
    }

    @Override
    public void create(House house) {
        houseRepository.save(house);
    }

    @Override
    public void update(House house) {
        houseRepository.findById(house.getId()).ifPresent(detail -> {
            detail.setUser(house.getUser());
            detail.setStorey(house.getStorey());
            detail.setCellName(house.getCellName());
            detail.setPosition(house.getPosition());
            }
        );
    }

    @Override
    public void delete(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public Page<House> findCostSetByNameLike(String name, Pageable pageable) {
        return null;
    }

    @Override
    public House findOne(Long id) {
        return null;
    }
}
