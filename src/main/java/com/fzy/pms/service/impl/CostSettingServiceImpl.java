package com.fzy.pms.service.impl;

import com.fzy.pms.dao.CostSettingRepository;
import com.fzy.pms.entity.pms.CostSetting;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.CostSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @program: CostSettingServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/04/17 23:22:35
 **/
@Service
public class CostSettingServiceImpl implements CostSettingService {

    @Autowired
    private CostSettingRepository costSettingRepository;

    @Override
    public Page<CostSetting> findAll(Pageable pageableDefault) {
        return costSettingRepository.findAll(pageableDefault);
    }

    @Override
    public void create(CostSetting costSetting) {
        costSettingRepository.save(costSetting);
    }

    @Override
    public void update(CostSetting costSetting) {
       costSettingRepository.findById(costSetting.getId()).ifPresent(e->{
           e.setCostName(costSetting.getCostName());
           e.setCostPrice(costSetting.getCostPrice());
           e.setRegular(costSetting.getRegular());
           costSettingRepository.save(e);
       });
    }

    @Override
    public void delete(Long id) {
        costSettingRepository.deleteById(id);
    }

    @Override
    public Page<CostSetting> findCostSetByNameLike(String name,Pageable pageable) {
        Page<CostSetting> costData = costSettingRepository.findCostSettingByCostNameLike("%" + name + "%", pageable);
        return costData;
    }

    @Override
    public CostSetting findOne(Long id) {
        return costSettingRepository.findById(id).orElseThrow(()->new SystemErrorException("费用不存在"));
    }
}
