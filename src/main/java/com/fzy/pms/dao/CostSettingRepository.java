package com.fzy.pms.dao;

import com.fzy.pms.entity.dto.RepairsReportDto;
import com.fzy.pms.entity.dto.SettleDto;
import com.fzy.pms.entity.pms.CostSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CostSettingRepository
 * @description:
 * @author: fzy
 * @date: 2019/04/17 23:27:29
 **/
@Repository
public interface CostSettingRepository extends JpaRepository<CostSetting,Long> {

    Page<CostSetting> findCostSettingByCostNameLike(String name, Pageable pageable);

/*    @Query("select new com.fzy.pms.entity.dto.SettleDto(user.id,user.username,user.realName," +
            "cost.costName,settle.totalPrice,settle.settingDate) from Settle settle" +
            "left join User user on user.id=settle.user_id" +
            "left join CostSetting cost on cost.id=settle.cost_id")
    List<SettleDto> findAllReportDto(Sort sort);


    @Query("select new com.fzy.pms.entity.dto.SettleDto(user.id,user.username,user.realName," +
            "cost.costName,settle.totalPrice,settle.settingDate) from Settle settle" +
            "left join User user on user.id=settle.user_id" +
            "left join CostSetting cost on cost.id=settle.cost_id where user.id= :userId")
    List<SettleDto> report(@Param("userId") Long userId, Sort sort);*/
}
