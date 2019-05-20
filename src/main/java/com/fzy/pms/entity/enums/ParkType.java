package com.fzy.pms.entity.enums;

import lombok.Getter;

/**
 * @program: ParkType
 * @description:
 * @author: fzy
 * @date: 2019/05/20 18:51:58
 **/
@Getter
public enum ParkType {

    SMALL_CAR("小车"),
    BIG_CAR("大车");

    private String name;

    ParkType(String name){
        this.name=name;
    }
}
