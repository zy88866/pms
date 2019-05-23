package com.fzy.pms.service.impl;

import com.fzy.pms.dao.AccountDetailRepository;
import com.fzy.pms.dao.UserRepository;
import com.fzy.pms.entity.dto.AccountDetailDto;
import com.fzy.pms.entity.dto.AccountDto;
import com.fzy.pms.entity.pms.AccountDetail;
import com.fzy.pms.entity.security.User;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @program: AccountServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/21 21:53:23
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountDetailRepository accountDetailRepository;

    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        return userRepository.findAccountAll(pageable);
    }

    @Override
    public Page<AccountDto> search(Long userId,Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new SystemErrorException("用户不存在"));
        return userRepository.findAccountByUserId(user.getUsername(),pageable);
    }

    @Override
    @Transactional
    public void payment(AccountDetail accountDetail) {
        userRepository.findById(accountDetail.getUser().getId()).ifPresent(detail->{
            detail.setBalance(detail.getBalance().add(accountDetail.getMoney()));
            userRepository.save(detail);
        });
        //设置充值时间
        accountDetail.setRechargeTime(new Date());
        accountDetailRepository.save(accountDetail);
    }

    @Override
    public List<AccountDetailDto> report(String userId) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        if(StringUtils.isBlank(userId) || userId.equals("null")){
            return accountDetailRepository.findAllDto(sort);
        }else{
            return accountDetailRepository.search(Long.parseLong(userId),sort);
        }
    }
}
