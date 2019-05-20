package com.fzy.pms.service.impl;

import com.fzy.pms.dao.DoorRepository;
import com.fzy.pms.entity.dto.DoorDto;
import com.fzy.pms.entity.enums.UserStatus;
import com.fzy.pms.entity.pms.Door;
import com.fzy.pms.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @program: DoorServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/19 09:19:15
 **/
@Service
public class DoorServiceImpl implements DoorService {

    @Autowired
    private DoorRepository doorRepository;

    @Override
    public Page<DoorDto> findAllDto(Pageable pageable) {
        return doorRepository.findAllDto(pageable);
    }

    @Override
    public void create(Door door) {
        door.setUserStatus(UserStatus.ENABLED);
        doorRepository.save(door);
    }

    @Override
    public DoorDto updateDoorStatus(Door door) {
        doorRepository.updateUserStatus(door);
        return doorRepository.findOneById(door.getId());
    }

    @Override
    public Page<DoorDto> search(Long id, Pageable pageable) {
        return Objects.isNull(id)?findAllDto(pageable):doorRepository.search(id,pageable);
    }
}
