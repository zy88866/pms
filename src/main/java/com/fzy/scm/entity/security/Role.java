package com.fzy.scm.entity.security;

import com.fzy.scm.entity.enums.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @program: Role
 * @description: 角色表
 * @author: fzy
 * @date: 2019/03/17 12:13:14
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="t_role")
@Data
@ApiModel("角色")
@SQLDelete(sql = "update t_role set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
public class Role extends Base {

    @Column(length = 25)
    @ApiModelProperty("名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("备注")
    @NotBlank(message = "备注不能为空")
    private String remark;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_menus",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    private Set<Menu> menus;

}
