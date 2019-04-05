package com.fzy.scm.service.Impl;

import com.fzy.scm.dao.MenuRepository;
import com.fzy.scm.entity.dto.MenuDTO;
import com.fzy.scm.entity.mapper.MenuMapper;
import com.fzy.scm.entity.security.Menu;
import com.fzy.scm.exception.SystemErrorException;
import com.fzy.scm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @program: MenuServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:24
 **/
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor ={RuntimeException.class,Exception.class})
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public MenuDTO findById(Long id) {
        return menuRepository.findById(id).map(e -> menuMapper.toDto(e)).orElseThrow(()->new SystemErrorException("查询的 菜单 id不存在"));
    }

    @Override
    public MenuDTO create(Menu menu) {
        return menuMapper.toDto(menuRepository.save(menu));
    }

    @Override
    public void update(Menu menu) {
        Optional<Menu> optional = menuRepository.findById(menu.getId());
        optional.ifPresent(e->{
            e.setComponent(menu.getComponent());
            e.setIcon(menu.getIcon());
            e.setName(menu.getName());
            e.setPath(menu.getPath());
            e.setPid(menu.getPid());
            menuRepository.save(e);
        });
    }

    @Override
    public void delete(Long id) {
       //1.查询全部子菜单
        List<Menu> menuList = menuRepository.findByPid(id);
        //2.删除子菜单
        menuList.forEach(e->{
            menuRepository.delete(e);
        });
        //3.删除父菜单
        menuRepository.deleteById(id);
    }


}
