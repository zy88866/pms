package com.fzy.scm.dao;

import com.fzy.scm.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @program: RoleRepository
 * @description:
 * @author: fzy
 * @date: 2019-04-05 10:45
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role ,Long> {


}
