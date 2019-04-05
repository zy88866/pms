package com.fzy.scm.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fzy.scm.entity.enums.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "t_menu")
@ApiModel("菜单")
@SQLDelete(sql = "update t_menu set delete_flag="+Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
public class Menu extends Base {

    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty("菜单名称")
    private String name;

    @Column(unique = true)
    @ApiModelProperty("排序")
    private Long sort;

    @NotBlank(message = "url 不能为空")
    @ApiModelProperty("跳转路径")
    private String path;

    @NotBlank(message = "图标不能为空")
    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("描述")
    private String component;

    @ApiModelProperty("父id")
    @Column(name = "pid",nullable = false)
    private Long pid;

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<Role> roles;
}
