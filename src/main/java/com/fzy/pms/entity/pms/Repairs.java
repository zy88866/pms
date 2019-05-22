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
import javax.validation.constraints.NotBlank;
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
@Table(name = "t_repairs")
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
    @NotBlank(message = "报修项目不能为空")
    private String repairsType;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("联系人")
    @NotBlank(message = "联系人不能为空")
    private String linkman;

    @ApiModelProperty("联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String linkPhone;

    @ApiModelProperty("联系地址")
    @NotBlank(message = "联系地址不能为空")
    private String linkAddress;

    @ApiModelProperty("报修时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairsDate;

    @ApiModelProperty("报修时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;

    @ApiModelProperty("报修单号")
    private String repairsBillNo;

    @ApiModelProperty("维修价格")
    private BigDecimal repairsPrice;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty("维修状态")
    private RepairsStatus repairsStatus;

}
