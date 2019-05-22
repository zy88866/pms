package com.fzy.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.RepairsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @program: RepairsDto
 * @description:
 * @author: fzy
 * @date: 2019/05/22 20:59:13
 **/
@Data
@AllArgsConstructor
public class RepairsDto {

    private Long id;

    private String realName;

    private String repairsBillNo;

    private String repairsType;

    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    private Date repairsDate;

    private RepairsStatus repairsStatus;

}
