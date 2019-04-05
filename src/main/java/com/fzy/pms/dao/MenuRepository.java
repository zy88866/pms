package com.fzy.pms.dao;

import com.fzy.pms.entity.security.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: MenuRepository
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:26
 **/
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long>, JpaSpecificationExecutor {

    /**
     * 根据菜单名查询
     * @param name
     * @return
     */
    Menu findByName(String name);

    /**
     * 根据父id 查询
     * @param Pid
     * @return
     */
    List<Menu> findByPid(Long Pid);
}
