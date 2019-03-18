package com.fzy.scm.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * @program: User
 * @description: 用户表
 * @author: fzy
 * @date: 2019/03/17 12:13:14
 **/
@Data
@Entity
@Table(name="t_user")
public class User extends Base implements UserDetails {

    private static final long serialVersionUID = 7320336762784228900L;

    public interface UserSimpleView{}

    public interface UserDataView extends UserSimpleView{}

    @NotBlank(message = "用户名不能为空")
    @Column(unique = true)
    @JsonView(UserSimpleView.class)
    private String userName;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDataView.class)
    private String password;

    @Email(message = "邮箱不符合规则")
    private String email;

    @Length(max = 11 ,min=1,message = "手机号必须是11位")
    private String phone;

    @NotBlank(message = "名字不能为空")
    private String realName;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private HashSet<Role> roles=new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> auth = new HashSet<>();
        List<Role> roles = Lists.newArrayList(this.getRoles());
        for (Role role : roles) {
            for(Privilege privilege:role.getPrivileges())
                auth.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return auth;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
}
