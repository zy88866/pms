package com.fzy.pms.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
}
