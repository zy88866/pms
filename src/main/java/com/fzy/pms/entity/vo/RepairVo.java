package com.fzy.pms.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: RepairVo
 * @description:
 * @author: fzy
 * @date: 2019/05/22 22:40:35
 **/
@Data
public class RepairVo {

    @NotNull(message = "id 不能为空")
    private Long id;

    private BigDecimal RepairsPrice;

    private String repairsBillNo;
}
