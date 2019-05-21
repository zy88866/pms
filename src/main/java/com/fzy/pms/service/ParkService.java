package com.fzy.pms.service;

import com.fzy.pms.entity.dto.ParkDto;
import com.fzy.pms.entity.pms.Park;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: ParkService
 * @description:
 * @author: fzy
 * @date: 2019/05/19 08:45:01
 **/
public interface ParkService extends BaseService<Park> {

    Page<ParkDto> findAllDto(Pageable pageable);

    Page<ParkDto> search(Long userId,Pageable pageable);

    ParkDto updateUseStatus(Park park);
}
