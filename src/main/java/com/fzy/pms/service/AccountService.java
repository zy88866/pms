package com.fzy.pms.service;

import com.fzy.pms.entity.dto.AccountDetailDto;
import com.fzy.pms.entity.dto.AccountDto;
import com.fzy.pms.entity.pms.AccountDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: AccountService
 * @description: 账户接口
 * @author: fzy
 * @date: 2019/05/21 21:52:50
 **/
public interface AccountService {

    /**
     * 查询全部账户
     * @param pageable
     * @return
     */
    Page<AccountDto> findAll(Pageable pageable);

    /**
     * 根据用户id 查询用户账户
     * @param userId
     * @return
     */
    Page<AccountDto> search(Long userId,Pageable pageable);

    /**
     * 账户充值
     * @param accountDetail
     */
    void payment(AccountDetail accountDetail);

    /**
     * 充值报表查询
     * @param userId
     * @return
     */
    List<AccountDetailDto> report(String userId);
}
