package com.fzy.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.RepairsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: RepairsDto
 * @description:
 * @author: fzy
 * @date: 2019/05/22 20:59:13
 **/
@Data
@AllArgsConstructor
public class RepairsReportDto {

    private Long id;

    private String realName;

    private String repairsBillNo;

    private BigDecimal repairsPrice;

    private String repairsType;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale = "zh", timezone = "GMT+8")
    private Date repairsDate;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale = "zh", timezone = "GMT+8")
    private Date finishDate;

}
