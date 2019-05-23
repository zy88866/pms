package com.fzy.pms.service;

import com.fzy.pms.entity.pms.CostSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: CostSettingService
 * @description:
 * @author: fzy
 * @date: 2019/04/17 23:19:51
 **/
public interface CostSettingService extends BaseService<CostSetting> {

    Page<CostSetting> findCostSetByNameLike(String name,Pageable pageable);

    List<CostSetting> findAll();

}
