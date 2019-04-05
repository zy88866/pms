package com.fzy.pms.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: MenuDTO
 * @description:
 * @author: fzy
 * @date: 2019-03-30 21:18
 **/
@Data
public class MenuDTO{

    private Long id;

    private String name;

    private Long sort;

    private String path;

    private String component;

    private Long pid;

    private String icon;

    private List<MenuDTO> children;
}
