package com.fzy.pms.service.impl;

import com.fzy.pms.dao.HouseRepository;
import com.fzy.pms.entity.pms.House;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

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
        Page<House> all = houseRepository.findAll(pageableDefault);
        if(!CollectionUtils.isEmpty(all.getContent())){
            all.getContent().forEach(detail->{
                detail.setUsername(detail.getUser().getUsername());
            });
        }
        return all;
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
            houseRepository.save(detail);
            }
        );
    }

    @Override
    public void delete(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public Page<House> findHouseByUserId(Long id,Pageable pageable) {
        Page<House> all = houseRepository.findHouseByUserId(id,pageable);
        if(!CollectionUtils.isEmpty(all.getContent())){
            all.getContent().forEach(detail->{
                detail.setUsername(detail.getUser().getUsername());
            });
        }
        return all;
    }

    @Override
    public House findOne(Long id) {
        House house = houseRepository.findById(id).orElseThrow(() -> new SystemErrorException("房产信息不存在"));
        house.setUsername(house.getUser().getId()+"");
        return house;
    }

    @Override
    public void batchDelete(Set<Long> ids) {
        houseRepository.deleteInBatch(ids);
    }
}
