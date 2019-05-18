package com.fzy.pms.dao;

import com.fzy.pms.entity.pms.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @program: HouseRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/09 13:35:22
 **/
@Repository
public interface HouseRepository extends JpaRepository<House ,Long> {

    @Transactional
    @Modifying
    @Query(value = "update t_house set delete_flag=1 where id in :ids",nativeQuery = true)
    void deleteInBatch(@Param("ids") Set<Long> ids);

    Page<House> findHouseByUserId(Long id, Pageable pageable);

}
