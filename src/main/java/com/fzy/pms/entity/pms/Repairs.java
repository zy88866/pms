package com.fzy.pms.entity.pms;

import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.RepairsStatus;
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
 * @program: Repairs
 * @description:
 * @author: fzy
 * @date: 2019/05/22 11:56:36
 **/
@Data
@ApiModel("报修管理")
@Table(name = "t_park")
@Entity
@SQLDelete(sql = "update t_repairs set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
@EqualsAndHashCode(callSuper=false)
public class Repairs extends Base{

    @ApiModelProperty("报修人")
    @NotNull(message = "报修人不能为空")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty("报修项目")
    private String RepairsType;

    @ApiModelProperty("报修时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date RepairsDate;

    @ApiModelProperty("报修单号")
    private String RepairsBillNo;

    @ApiModelProperty("维修价格")
    private BigDecimal RepairsPrice;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("维修状态")
    private RepairsStatus repairsStatus;

}
