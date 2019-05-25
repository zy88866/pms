package com.fzy.pms.dao;

import com.fzy.pms.entity.dto.DoorDto;
import com.fzy.pms.entity.pms.Door;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @program: DoorRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/19 09:43:24
 **/
@Repository
public interface DoorRepository extends JpaRepository<Door, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Door door set door.useStatus= :#{#door.useStatus} where id= :#{#door.id}")
    void updateuseStatus(@Param("door") Door door);

    @Query("SELECT new com.fzy.pms.entity.dto.DoorDto(door.id ,user.username,door.useStatus,door.expireDate,door.doorType) FROM Door door " +
            "left join User user on user.id=door.user where door.id= :id")
    DoorDto findOneById(@Param("id") Long id);

    @Query("SELECT new com.fzy.pms.entity.dto.DoorDto(door.id ,user.username,door.useStatus,door.expireDate,door.doorType) FROM Door door " +
            "left join User user on user.id=door.user ")
    Page<DoorDto> findAllDto(Pageable pageable);

    @Query("SELECT new com.fzy.pms.entity.dto.DoorDto(door.id ,user.username,door.useStatus,door.expireDate,door.doorType) FROM Door door " +
            "left join User user on user.id=door.user where user.id= :userId")
    Page<DoorDto> search(@Param("userId") Long userId, Pageable pageable);
}
