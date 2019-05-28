package com.fzy.pms.service;

import com.fzy.pms.entity.dto.SettleDto;
import com.fzy.pms.entity.pms.Settle;

import java.util.List;

/**
 * @program: SettleService
 * @description:
 * @author: fzy
 * @date: 2019/05/23 08:32:41
 **/
public interface SettleService extends BaseService<Settle> {

    /**
     * 充值报表查询
     * @param userId
     * @return
     */
    List<SettleDto> report(String userId);
}
