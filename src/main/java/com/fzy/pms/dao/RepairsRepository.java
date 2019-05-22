package com.fzy.pms.dao;

import com.fzy.pms.entity.dto.RepairsDto;
import com.fzy.pms.entity.pms.Repairs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @program: RepairsRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/22 12:06:21
 **/
@Repository
public interface RepairsRepository extends JpaRepository<Repairs,Long> {

    @Query("select new com.fzy.pms.entity.dto.RepairsDto(repairs.id,user.realName, repairs.repairsBillNo," +
            "repairs.repairsType,repairs.repairsDate,repairs.repairsStatus) from Repairs repairs " +
            "left join User user on user.id=repairs.user where repairs.repairsStatus='NOT' or repairs.repairsStatus='ING'")
    Page<RepairsDto> findAllDto(Pageable pageable);


    @Query("select new com.fzy.pms.entity.dto.RepairsDto(repairs.id,user.realName,repairs.repairsBillNo," +
            "repairs.repairsType,repairs.repairsDate,repairs.repairsStatus) from Repairs repairs " +
            "left join User user on user.id=repairs.user where user.id= :userId and (repairs.repairsStatus='NOT' or repairs.repairsStatus='ING')")
    Page<RepairsDto> search(@Param("userId") Long userId, Pageable pageable);
}
