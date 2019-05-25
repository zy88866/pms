package com.fzy.pms.entity.pms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.UseStatus;
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

    @ApiModelProperty("使用状态")
    @Enumerated(EnumType.STRING)
    private UseStatus useStatus= UseStatus.ENABLED;

    @ApiModelProperty("开门方式")
    @Enumerated(EnumType.STRING)
    private DoorType doorType;

    @ApiModelProperty("用户")
    @NotNull(groups = {Update.class, Save.class},message = "用户不能为空")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("到期日期")
    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    public interface Save {
    }

}
