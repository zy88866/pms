package com.fzy.pms.entity.pms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.ParkType;
import com.fzy.pms.entity.enums.UserStatus;
import com.fzy.pms.entity.security.Base;
import com.fzy.pms.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: Park
 * @description: 车位管理
 * @author: fzy
 * @date: 2019/05/18 22:11:04
 **/
@Data
@ApiModel("车位管理")
@Table(name = "t_park")
@Entity
@SQLDelete(sql = "update t_park set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
@EqualsAndHashCode(callSuper=false)
public class Park extends Base {

    @ApiModelProperty("用户")
    @NotNull(groups = {Update.class, Park.Save.class},message = "用户不能为空")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("位置")
    @NotBlank(message = "位置不能为空")
    private String position;

    @ApiModelProperty("车位类型")
    @Enumerated(EnumType.STRING)
    private ParkType parkType;

    @ApiModelProperty("到期日期")
    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @ApiModelProperty("使用状态")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public interface Save {
    }

}
