package com.fzy.pms.service;

import com.fzy.pms.entity.pms.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @program: HouseService
 * @description:
 * @author: fzy
 * @date: 2019/05/09 13:37:37
 **/
public interface HouseService {

    Page<House> findAll(Pageable pageableDefault);

    void create(House house);

    void update(House house);

    void delete(Long id);

    Page<House> findHouseByUserId(Long id,Pageable pageable);

    House findOne(Long id);

    void batchDelete(Set<Long> ids);
}
