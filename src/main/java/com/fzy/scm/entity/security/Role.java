package com.fzy.scm.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @program: Role
 * @description: 角色表
 * @author: fzy
 * @date: 2019/03/17 12:13:14
 **/
@Entity
@Table(name="t_role")
@Getter
@Setter
@ApiModel("角色")
public class Role extends Base {

    @Column(length = 25)
    @ApiModelProperty("名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("备注")
    @NotBlank(message = "备注不能为空")
    private String remark;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_menus",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    private Set<Menu> menus;


    @Override
    public String toString() {
        return "Role{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
