package com.fzy.pms.service.impl;

import com.fzy.pms.dao.RoleRepository;
import com.fzy.pms.dao.UserRepository;
import com.fzy.pms.entity.dto.MenuDto;
import com.fzy.pms.entity.dto.RoleDto;
import com.fzy.pms.entity.mapper.RoleMapper;
import com.fzy.pms.entity.security.Menu;
import com.fzy.pms.entity.security.Role;
import com.fzy.pms.entity.security.User;
import com.fzy.pms.exception.SystemErrorException;
import com.fzy.pms.service.MenuService;
import com.fzy.pms.service.RoleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;
import org.thymeleaf.util.SetUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: RoleServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019-04-05 11:10
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuService menuService;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role create(Role role) {
        if(roleRepository.findRoleByName(role.getName()).isPresent()){
            throw new SystemErrorException("角色名称已存在");
        }
        return roleRepository.save(role);
    }

    @Override
    public void update(Role role) {
        roleRepository.findRoleByName(role.getName()).ifPresent(dbRole ->{
           if(!dbRole.getId().equals(role.getId())){
               throw new SystemErrorException("角色名称已存在");
           }
        });
        roleRepository.findById(role.getId()).ifPresent(roles ->{
            roles.setName(role.getName());
            roles.setRemark(role.getRemark());
            roles.setMenus(role.getMenus());
            roleRepository.save(roles);
        });
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDto> findAll() {
        Sort sort=new Sort(Sort.Direction.DESC, Collections.singletonList("id"));
        List<RoleDto> list = roleMapper.toDto(roleRepository.findAll(sort));
        Map<Long, Integer> map = getCiteNum(list);
        list.forEach(role->{
            role.setCiteNum(Objects.isNull(map.get(role.getId()))?0:map.get(role.getId()));
        });
        return list;
    }

    @Override
    public int batchDelete(Set<Long> ids) {
        if(SetUtils.isEmpty(ids)){
            throw new SystemErrorException("ids 不能为空");
        }
        return  roleRepository.deleteRoleByIdIn(ids)==ids.size()?ids.size():0;
    }

    @Override
    public List<RoleDto> search(String name) {
        Sort sort=new Sort(Sort.Direction.DESC, Collections.singletonList("id"));
        List<RoleDto> list = roleMapper.toDto(roleRepository.findRoleByNameLike("%" + name + "%",sort));
        Map<Long, Integer> map = getCiteNum(list);
        list.forEach(roles->{
            roles.setCiteNum(Objects.isNull(map.get(roles.getId()))?0:map.get(roles.getId()));
        });
        return list;
    }

    /**
     * 获取当前角色的用户应用数
     * @return
     */
    private Map<Long, Integer> getCiteNum(List<RoleDto> list){
        List<User> userList = userRepository.findAll();
        Map<Long,Integer> map= Maps.newHashMapWithExpectedSize(list.size());
        userList.forEach(user->{
            Long id=user.getRole().getId();
            Integer num = map.get(id);
            map.put(id, num == null ? 1 : num + 1);
        });
        return map;
    }

    @Override
    @Transactional
    public Role findOne(Long id) {
        if(Objects.isNull(id)){
            throw new SystemErrorException("id 不能为空");
        }
        Role role = roleRepository.findById(id).orElseThrow(() ->new SystemErrorException("用户不存在"));
        if(!SetUtils.isEmpty(role.getMenus())){

            Set<Menu> menus = role.getMenus().stream().filter(item -> item.getPid() != 0L).collect(Collectors.toSet());
            role.setMenus(menus);
        }
        return role;
    }
}
