package com.fzy.pms.service.impl;

import com.fzy.pms.dao.RoleRepository;
import com.fzy.pms.entity.security.Role;
import com.fzy.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
