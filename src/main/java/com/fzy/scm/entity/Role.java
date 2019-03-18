package com.fzy.scm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @program: Role
 * @description: 角色表
 * @author: fzy
 * @date: 2019/03/17 12:13:14
 **/
@Entity
@Table(name="t_role")
@Data
public class Role extends Base {

    private static final long serialVersionUID = 598336330794967836L;

    @Column(length = 25)
    private String name;

    //停用或启用
    private Boolean available;

    //懒加载 不会查询role表
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users;

    //急加载 会查询role表
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<Privilege> privileges;

}
