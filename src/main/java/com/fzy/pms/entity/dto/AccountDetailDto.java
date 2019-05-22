package com.fzy.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.RechargeType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: AccountDetailDto
 * @description:
 * @author: fzy
 * @date: 2019/05/22 08:22:01
 **/
@Data
@AllArgsConstructor
public class AccountDetailDto {

    private Long userId;

    private String username;

    private String realName;

    private String phone;

    private BigDecimal money;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",locale = "zh", timezone = "GMT+8")
    private Date rechargeTime ;

    private RechargeType rechargeType;

}
