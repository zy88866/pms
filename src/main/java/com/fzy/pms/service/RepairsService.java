package com.fzy.pms.service;

import com.fzy.pms.entity.dto.RepairsDto;
import com.fzy.pms.entity.pms.Repairs;
import com.fzy.pms.entity.vo.RepairVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: RepairsService
 * @description:
 * @author: fzy
 * @date: 2019/05/22 12:09:05
 **/
public interface RepairsService extends BaseService<Repairs> {

    Page<RepairsDto> findAllDto(Pageable pageable);

    /**
     * 开始派单
     * @param repairVo
     */
    void startDispatch(RepairVo repairVo);

    /**
     * 结束订单
     * @param repairVo
     */
    void endDispatch(RepairVo repairVo);

    /**
     * 订单中心搜索
     * @param userId
     * @param pageable
     * @return
     */
    Page<RepairsDto> search(Long userId,Pageable pageable);



}
