package com.fzy.scm.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
public class Role extends Base {

    private static final long serialVersionUID = 598336330794967836L;

    @Column(length = 25)
    private String name;

    //停用或启用
    private Boolean available;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    @JsonIgnore
    @ManyToMany
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
                ", available='" + available + '\'' +
                '}';
    }
}
