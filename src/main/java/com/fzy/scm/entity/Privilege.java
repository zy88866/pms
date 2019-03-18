package com.fzy.scm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @program: Privilege
 * @description: 权限表
 * @author: fzy
 * @date: 2019/03/17 14:04:28
 **/
@Entity
@Table(name = "t_privilege")
@Data
public class Privilege extends Base {

    private static final long serialVersionUID = -7187155846058661556L;

    //图标
    private String icon;

    //权限名称
    private String name;

    //权限路径
    private String url;

    //父节点id
    private Long pid;

    //懒加载  不会查询role表
    @ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
