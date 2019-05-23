package com.fzy.pms.service.impl;

import com.fzy.pms.dao.CostSettingRepository;
import com.fzy.pms.dao.SettleRepository;
import com.fzy.pms.dao.UserRepository;
import com.fzy.pms.entity.pms.CostSetting;
import com.fzy.pms.entity.pms.Settle;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.SettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

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
    public void create(Settle settle) {
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
}
