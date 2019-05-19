package com.fzy.pms.service;

import com.fzy.pms.entity.pms.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @program: HouseService
 * @description:
 * @author: fzy
 * @date: 2019/05/09 13:37:37
 **/
public interface HouseService  extends  BaseService<House> {

    Page<House> findHouseByUserId(Long id,Pageable pageable);

}
