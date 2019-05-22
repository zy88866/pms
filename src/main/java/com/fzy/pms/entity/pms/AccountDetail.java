package com.fzy.pms.entity.pms;

import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.RechargeType;
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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: AccountDetail
 * @description:
 * @author: fzy
 * @date: 2019/05/21 15:04:02
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("账户")
@Table(name = "t_account_detail")
@Entity
@SQLDelete(sql = "update t_account_detail set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
public class AccountDetail extends Base {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("充值金额")
    @NotNull(message = "充值金额不能为空")
    private BigDecimal money;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty("充值时间")
    @NotNull(message = "充值时间不能为空")
    private Date rechargeTime ;

    @ApiModelProperty("充值方式")
    @NotNull(message = "充值方式不能为空")
    @Enumerated(EnumType.STRING)
    private RechargeType rechargeType;

}
