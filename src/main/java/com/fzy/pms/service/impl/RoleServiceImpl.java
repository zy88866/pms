package com.fzy.pms.service.impl;

import com.fzy.pms.dao.RoleRepository;
import com.fzy.pms.entity.dto.RoleDto;
import com.fzy.pms.entity.mapper.RoleMapper;
import com.fzy.pms.entity.security.Role;
import com.fzy.pms.service.RoleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void update(Role role) {

    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDto> findAll() {
        Sort sort=new Sort(Sort.Direction.DESC, Collections.singletonList("id"));
        return roleMapper.toDto(roleRepository.findAll(sort));
    }
}
