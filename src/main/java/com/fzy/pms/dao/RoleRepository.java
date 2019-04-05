package com.fzy.pms.dao;

import com.fzy.pms.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: RoleRepository
 * @description:
 * @author: fzy
 * @date: 2019-04-05 10:45
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role ,Long> {


}
