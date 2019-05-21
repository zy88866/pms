package com.fzy.pms.dao;

import com.fzy.pms.entity.security.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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
     * 根据父id 查询
     * @param Pid
     * @return
     */
    List<Menu> findByPid(Long Pid);

    /**
     * 根据角色查询菜单列表
     * @param id
     * @return
     */
    @Query(value = "select b.* from roles_menus a left join t_menu b on a.menu_id=b.id where a.role_id=?1",nativeQuery=true)
    List<Menu> findByRole(Long id);
}
