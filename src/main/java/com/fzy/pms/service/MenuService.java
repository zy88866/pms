package com.fzy.pms.service;

import com.fzy.pms.entity.dto.MenuDTO;
import com.fzy.pms.entity.security.Menu;

/**
 * @program: MenuService
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:17
 **/
public interface MenuService {

    /**
     * 根据id 查询菜单
     * @param id
     * @return
     */
    MenuDTO findById(Long id);

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    MenuDTO create(Menu menu);

    /**
     * 更新菜单
     * @param menu
     */
    void update(Menu menu);

    /**
     * 删除id
     * @param id
     */
    void delete(Long id);

}
