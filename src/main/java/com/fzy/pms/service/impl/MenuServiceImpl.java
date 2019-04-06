package com.fzy.pms.service.impl;

import com.fzy.pms.dao.MenuRepository;
import com.fzy.pms.entity.dto.MenuDto;
import com.fzy.pms.entity.dto.UserDto;
import com.fzy.pms.entity.mapper.MenuMapper;
import com.fzy.pms.entity.security.Menu;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.MenuService;
import com.fzy.pms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private UserService userService;

    @Override
    public Set<MenuDto> findByMenuTree() {
        //分组后的menu列表
        Map<Long, List<Menu>> menuList = menuRepository.findAll()
                .stream().collect(Collectors.groupingBy(Menu::getPid));
        return createTree(0L,menuList);
    }

    @Override
    public Set<MenuDto> getCurrMenuTree() {
        if(userService.getCurrUserInfo().isPresent()){
            UserDto userInfo = userService.getCurrUserInfo().get();
            List<Menu> menus = menuRepository.findByRole(userInfo.getRole().getId());
            if(!ListUtils.isEmpty(menus)){
                Map<Long, List<Menu>> listMenu = menus.stream().collect(Collectors.groupingBy(Menu::getPid));
                return createTree(0L, listMenu);
            }
        }
        throw new SystemErrorException("当前用户不存在!!!");
    }

    @Override
    public MenuDto create(Menu menu) {
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

    /**
     * 构建菜单树
     * @param parentId
     * @param menus
     * @return
     */
    private Set<MenuDto> createTree(Long parentId, Map<Long, List<Menu>> menus){
        return menus.get(parentId).stream().map(e->{
            MenuDto menuDTO=new MenuDto();
            BeanUtils.copyProperties(e,menuDTO);
            if(!Objects.isNull(menus.get(e.getId()))){
                menuDTO.setChildren(createTree(e.getId() ,menus));
            }
            return menuDTO;
        }).collect(Collectors.toSet());
    }

}
