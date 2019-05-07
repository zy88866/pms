package com.fzy.pms.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @program: RoleDto
 * @description:
 * @author: fzy
 * @date: 2019/04/06 16:02:07
 **/
@Data
public class RoleDto {

    private Long id;

    private String name;

    private String remark;

    @ApiModelProperty("权限所包含的用户数")
    private int citeNum;

    @ApiModelProperty("菜单列表")
    private Set<MenuDto> menus;
}
