package com.fzy.pms.dao;

import com.fzy.pms.entity.pms.Settle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: SettleRepository
 * @description:
 * @author: fzy
 * @date: 2019/05/23 08:31:49
 **/
@Repository
public interface SettleRepository extends JpaRepository<Settle, Long> {


}
