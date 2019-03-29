package com.fzy.scm.dao;

import com.fzy.scm.entity.Cost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @program: CostRepository
 * @description:
 * @author: fzy
 * @date: 2019/03/29 21:05:20
 **/
@Repository
public interface CostRepository extends CrudRepository<Cost, Long> {

    @Override
    Optional<Cost> findById(Long id);

}
