package com.fzy.pms.entity.dto;

import com.fzy.pms.entity.security.Role;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: UserDto
 * @description:
 * @author: fzy
 * @date: 2019-04-05 15:40
 **/
@Data
public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String realName;

    private RoleDto role;

}
