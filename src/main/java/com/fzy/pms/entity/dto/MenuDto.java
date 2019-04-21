package com.fzy.pms.entity.dto;

import lombok.Data;

import java.util.Set;

/**
 * @program: MenuDto
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:18
 **/
@Data
public class MenuDto implements Comparable {

    private Long id;

    private String name;

    private String path;

    private String component;

    private String icon;

    private Set<MenuDto> children;

    @Override
    public int compareTo(Object obj) {
        //判断是否属于Student类型，否则抛异常
        if (! (obj instanceof MenuDto)) throw new RuntimeException("NotSuchTypeException");

        MenuDto m= (MenuDto) obj;
        return this.id.compareTo(m.id);
    }
}
