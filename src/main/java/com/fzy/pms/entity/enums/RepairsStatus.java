package com.fzy.pms.entity.enums;

import lombok.AllArgsConstructor;

/**
 * @program: RepairsStatus
 * @description:
 * @author: fzy
 * @date: 2019/05/22 12:01:04
 **/
@AllArgsConstructor
public enum RepairsStatus {

     NOT("未完成"),
     ING("进行中"),
     YES("已完成");

    private String name;

}
