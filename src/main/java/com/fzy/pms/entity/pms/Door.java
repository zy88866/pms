package com.fzy.pms.entity.pms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.DoorStatus;
import com.fzy.pms.entity.enums.DoorType;
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
import java.util.Date;

/**
 * @program: Door
 * @description: 门禁
 * @author: fzy
 * @date: 2019/05/18 22:08:32
 **/
@Data
@ApiModel("门禁管理")
@Table(name = "t_door")
@Entity
@SQLDelete(sql = "update t_door set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
@EqualsAndHashCode(callSuper=false)
public class Door extends Base {

    @ApiModelProperty("门禁状态")
    @Enumerated(EnumType.STRING)
    private DoorStatus doorStatus;

    @ApiModelProperty("开门方式")
    @Enumerated(EnumType.STRING)
    private DoorType doorType;

    @ApiModelProperty("用户")
    @NotNull(groups = {Update.class, CostSetting.Save.class},message = "用户不能为空")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("到期日期")
    @JsonFormat(pattern="yyyy-mm-dd HH:mm:ss",timezone="GMT+8")
    private Date expireDate;

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
