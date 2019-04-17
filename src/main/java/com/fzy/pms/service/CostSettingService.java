package com.fzy.pms.service;

import com.fzy.pms.entity.pms.CostSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: CostSettingService
 * @description:
 * @author: fzy
 * @date: 2019/04/17 23:19:51
 **/
public interface CostSettingService {

    Page<CostSetting> findAll(Pageable pageableDefault);

    void create(CostSetting costSetting);

    void update(CostSetting costSetting);

    void delete(Long id);
}
