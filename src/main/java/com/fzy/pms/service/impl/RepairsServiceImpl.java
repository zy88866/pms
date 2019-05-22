package com.fzy.pms.service.impl;

import com.fzy.pms.dao.RepairsRepository;
import com.fzy.pms.entity.dto.RepairsDto;
import com.fzy.pms.entity.dto.RepairsReportDto;
import com.fzy.pms.entity.enums.RepairsStatus;
import com.fzy.pms.entity.pms.Repairs;
import com.fzy.pms.entity.vo.RepairVo;
import com.fzy.pms.service.RepairsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: RepairsServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/05/22 12:09:25
 **/
@Service
public class RepairsServiceImpl implements RepairsService {

    @Autowired
    private RepairsRepository repairsRepository;

    @Override
    public void create(Repairs repairs) {
        repairs.setRepairsStatus(RepairsStatus.NOT);
        repairs.setRepairsDate(new Date());
        repairs.setRepairsBillNo("BX"+System.currentTimeMillis());
        repairsRepository.save(repairs);
    }

    @Override
    public Page<RepairsDto> findAllDto(Pageable pageable) {
        return repairsRepository.findAllDto(pageable);
    }

    @Override
    public void startDispatch(RepairVo repairVo) {
        repairsRepository.findById(repairVo.getId()).ifPresent(detail ->{
            detail.setRepairsStatus(RepairsStatus.ING);
            detail.setRepairsPrice(repairVo.getRepairsPrice());
            repairsRepository.save(detail);
        });
    }

    @Override
    public void endDispatch(RepairVo repairVo) {
        repairsRepository.findById(repairVo.getId()).ifPresent(detail ->{
            detail.setRepairsStatus(RepairsStatus.YES);
            detail.setFinishDate(new Date());
            repairsRepository.save(detail);
        });
    }

    @Override
    public Page<RepairsDto> search(Long userId, Pageable pageable) {
        return repairsRepository.search(userId,pageable);
    }

    @Override
    public List<RepairsReportDto> report(String userId) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        if(StringUtils.isBlank(userId)||userId.equals("null")){
            return repairsRepository.findAllReportDto(sort);
        }else {
            return repairsRepository.report(Long.parseLong(userId),sort);
        }
    }
}
