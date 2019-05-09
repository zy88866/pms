package com.fzy.pms.entity.pms;

import com.fzy.pms.entity.enums.Constants;
import com.fzy.pms.entity.enums.Storey;
import com.fzy.pms.entity.security.Base;
import com.fzy.pms.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

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
public class House extends Base {

    @ApiModelProperty("用户")
    private User user;

    @ApiModelProperty("位置")
    private String position;

    @ApiModelProperty("类型")
    private Storey storey;

    @ApiModelProperty("小区名称")
    private String CellName;

}
