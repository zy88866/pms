package com.fzy.pms.entity.pms;

import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.security.Base;
import com.fzy.pms.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: Settle
 * @description:
 * @author: fzy
 * @date: 2019/05/23 08:24:34
 **/
@Data
@ApiModel("结算表")
@Table(name = "t_settle")
@Entity
@SQLDelete(sql = "update t_settle set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
@EqualsAndHashCode(callSuper=false)
public class Settle extends Base {

    @ApiModelProperty("结算账户")
    @NotNull(message = "结算账户不能为空")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("费用类型")
    @NotNull(message = "费用类型不能为空")
    @ManyToOne
    @JoinColumn(name = "cost_id")
    private CostSetting costSetting;

    @ApiModelProperty("倍数")
    private BigDecimal multiple;

    @ApiModelProperty("结算时间")
    private Date settingDate;

    @ApiModelProperty("扣除金额")
    private BigDecimal totalPrice;

}
