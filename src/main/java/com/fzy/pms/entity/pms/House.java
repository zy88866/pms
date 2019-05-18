package com.fzy.pms.entity.pms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.Storey;
import com.fzy.pms.entity.security.Base;
import com.fzy.pms.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @program: House
 * @description: 房产
 * @author: fzy
 * @date: 2019/05/09 10:01:00
 **/
@Data
@ApiModel("房产管理")
@Table(name = "t_house")
@Entity
@SQLDelete(sql = "update t_house set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
@EqualsAndHashCode(callSuper=false)
public class House extends Base {

    /**
     * optional 属性是定义该关联类对是否必须存在，值为false时，
     * 关联类双方都必须存在，如果关系被维护端不存在，查询的结果为null。
     * 值为true 时, 关系被维护端可以不存在，查询的结果仍然会返回关系维护端，
     * 在关系维护端中指向关系被维护端的属性为null。
     * optional 属性的默认值是true。
     */

    @ApiModelProperty("用户")
    @NotNull(groups = {Update.class, CostSetting.Save.class},message = "用户不能为空")
    @ManyToOne(fetch =FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("位置")
    private String position;

    /**
     * EnumType:  ORDINAL 枚举序数  默认选项（int）。eg:TEACHER 数据库存储的是 0
     *            STRING：枚举名称       (String)。eg:TEACHER 数据库存储的是 "TEACHER"
     */
    @ApiModelProperty("类型")
    @Enumerated(EnumType.STRING)
    private Storey storey;

    @ApiModelProperty("小区名称")
    private String cellName;

    public interface Save {
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    @ApiModelProperty("用户名")
    @Transient
    private String username;

}
