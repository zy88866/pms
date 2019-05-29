package com.fzy.pms.service.impl;

import com.fzy.pms.dao.CostSettingRepository;
import com.fzy.pms.dao.SettleRepository;
import com.fzy.pms.dao.UserRepository;
import com.fzy.pms.entity.dto.AccountDetailDto;
import com.fzy.pms.entity.dto.SettleDto;
import com.fzy.pms.entity.pms.CostSetting;
import com.fzy.pms.entity.pms.Settle;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.SettleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @program: SettleServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/23 08:32:51
 **/
@Service
public class SettleServiceImpl implements SettleService {

    @Autowired
    private SettleRepository settleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CostSettingRepository costSettingRepository;

    @Override
    @Transactional
    public synchronized void create(Settle settle) {
        userRepository.findById(settle.getUser().getId()).ifPresent(user ->{
            CostSetting costSetting = costSettingRepository.findById(settle.getCostSetting().getId()).orElseThrow(() -> new SystemErrorException("费用不存在"));
            BigDecimal total = costSetting.getCostPrice().multiply(settle.getMultiple());
            settle.setTotalPrice(total);
            user.setBalance(user.getBalance().subtract(total));
            userRepository.save(user);
        });
        settle.setSettingDate(new Date());
        settleRepository.save(settle);
    }

    @Override
    public List<SettleDto> report(String userId) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        if(StringUtils.isBlank(userId) || userId.equals("null")){
            return costSettingRepository.findAllReportDto(sort);
        }else{
            return costSettingRepository.report(Long.parseLong(userId),sort);
        }
    }
}
