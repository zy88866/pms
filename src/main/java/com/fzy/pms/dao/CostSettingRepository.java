package com.fzy.pms.dao;

import com.fzy.pms.entity.pms.CostSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: CostSettingRepository
 * @description:
 * @author: fzy
 * @date: 2019/04/17 23:27:29
 **/
@Repository
public interface CostSettingRepository extends JpaRepository<CostSetting,Long> {

    Page<CostSetting> findCostSettingByCostNameLike(String name, Pageable pageable);
}
