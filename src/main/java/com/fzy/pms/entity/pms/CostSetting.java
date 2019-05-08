package com.fzy.pms.entity.pms;

import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.security.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: CostSetting
 * @description: 费用设置
 * @author: fzy
 * @date: 2019/04/17 23:10:40
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("费用设置")
@Table(name = "t_cost")
@Entity
@SQLDelete(sql = "update t_cost set delete_flag="+ Constants.DELETED+" where id= ?")
@Where(clause = "delete_flag="+ Constants.NORMEL)
public class CostSetting extends Base {

    @ApiModelProperty("费用名称")
    @NotBlank(groups = {Update.class,Save.class},message = "费用名称不能为空")
    private String costName;

    @ApiModelProperty("费用价格")
    @NotNull(groups = {Update.class,Save.class},message = "费用价格不能为空")
    @Column(precision=19,scale=8)
    private BigDecimal costPrice;

    @ApiModelProperty("计费规则")
    @NotBlank(groups = {Update.class,Save.class},message = "计费规则不能为空")
    private String regular;

    public interface Save {
    }

}
