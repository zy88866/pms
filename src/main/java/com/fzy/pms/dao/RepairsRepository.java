package com.fzy.pms.dao;

import com.fzy.pms.entity.pms.Repairs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: RepairsRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/22 12:06:21
 **/
@Repository
public interface RepairsRepository extends JpaRepository<Repairs,Long> {

}
