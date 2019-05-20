package com.fzy.pms.dao;

import com.fzy.pms.entity.pms.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: ParkRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/20 19:03:24
 **/
@Repository
public interface ParkRepository extends JpaRepository<Park,Long> {

}
