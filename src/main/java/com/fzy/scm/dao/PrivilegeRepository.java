package com.fzy.scm.dao;

import com.fzy.scm.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: PrivilegeRepository
 * @description:
 * @author: fzy
 * @date: 2019/03/17 22:38:16
 **/
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {

    @Override
    <S extends Privilege> S save(S entity);
}
