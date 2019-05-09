package com.fzy.pms.dao;

import com.fzy.pms.entity.pms.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: HouseRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/09 13:35:22
 **/
@Repository
public interface HouseRepository extends JpaRepository<House ,Long> {

}
