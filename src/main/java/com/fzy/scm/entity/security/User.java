package com.fzy.scm.entity.security;

import com.fzy.scm.entity.enums.Constants;
import com.google.common.collect.Sets;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

/**
 * @program: User
 * @description: 用户表
 * @author: fzy
 * @date: 2019/03/17 12:13:14
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="t_user")
@ApiModel("用户")
public class User extends Base implements UserDetails {

    @NotBlank(message = "用户名不能为空")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Email(message = "邮箱不符合规则")
    private String email;

    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机号码不符合规范")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @NotBlank(message = "名字不能为空")
    private String realName;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName="id",nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Sets.newHashSet(new SimpleGrantedAuthority(role.getName()));
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
        return getDeleteFlag()==Integer.parseInt(Constants.NORMEL);
    }

}
