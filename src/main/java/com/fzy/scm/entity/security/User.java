package com.fzy.scm.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: User
 * @description: 用户表
 * @author: fzy
 * @date: 2019/03/17 12:13:14
 **/
@Getter
@Setter
@Entity
@Table(name="t_user")
public class User extends Base implements UserDetails {

    private static final long serialVersionUID = 7320336762784228900L;

    public interface UserSimpleView{}

    public interface UserDataView extends UserSimpleView{}

    @NotBlank(message = "用户名不能为空")
    @Column(unique = true)
    @JsonView(UserSimpleView.class)
    private String username;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDataView.class)
    private String password;

    @Email(message = "邮箱不符合规则")
    private String email;

    @Length(max = 11 ,min=1,message = "手机号必须是11位")
    private String phone;

    @NotBlank(message = "名字不能为空")
    private String realName;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auth = new HashSet<>();
        List<Role> roles = Lists.newArrayList(this.getRoles());
        for (Role role : roles) {
                auth.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auth;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getDeleteFlag()==0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", username='" + username + '\''+
                ", realName='" + realName + '\''+
                '}';
    }
}
