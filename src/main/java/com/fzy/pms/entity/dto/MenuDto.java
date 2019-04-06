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
public class MenuDto {

    private Long id;

    private String name;

    private String path;

    private String component;

    private String icon;

    private Set<MenuDto> children;
}
