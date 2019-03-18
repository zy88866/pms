package com.fzy.scm.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @program: Menu
 * @description:
 * @author: fzy
 * @date: 2019/03/18 22:16:27
 **/
@Entity
@Data
@Table(name = "t_menu")
public class Menu extends Base {

    @NotBlank
    private String name;

    @Column(unique = true)
    private Long sort;

    private String path;

    private String icon;

    /**
     * 上级菜单ID
     */
    @Column(name = "pid",nullable = false)
    private Long pid;

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles;
}
