package com.fzy.pms.dao;

import com.fzy.pms.entity.dto.ParkDto;
import com.fzy.pms.entity.pms.Door;
import com.fzy.pms.entity.pms.Park;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: ParkRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/20 19:03:24
 **/
@Repository
public interface ParkRepository extends JpaRepository<Park,Long> {

    @Query("SELECT new com.fzy.pms.entity.dto.ParkDto(park.id ,user.username,park.useStatus,park.expireDate,park.parkType,park.position) FROM Park park " +
            "left join User user on user.id=park.user ")
    Page<ParkDto> findAllDto(Pageable pageable);

    @Query("SELECT new com.fzy.pms.entity.dto.ParkDto(park.id ,user.username,park.useStatus,park.expireDate,park.parkType,park.position) FROM Park park " +
            "left join User user on user.id=park.user where user.id= :userId")
    Page<ParkDto> search(@Param("userId") Long userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Park park set park.useStatus= :#{#park.useStatus} where id= :#{#park.id}")
    void updateUseStatus(@Param("park") Park park);

    @Query("SELECT new com.fzy.pms.entity.dto.ParkDto(park.id ,user.username,park.useStatus,park.expireDate,park.parkType,park.position) FROM Park park " +
            "left join User user on user.id=park.user where park.id=?1")
    ParkDto findOne(Long id);


}
