package com.fzy.pms.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: AccountDto
 * @description:
 * @author: fzy
 * @date: 2019/05/21 21:55:41
 **/
@Data
@AllArgsConstructor
public class AccountDto {

    private Long userId;

    private String username;

    private BigDecimal balance;
}
