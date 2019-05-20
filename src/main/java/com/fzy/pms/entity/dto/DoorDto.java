package com.fzy.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.DoorStatus;
import com.fzy.pms.entity.enums.DoorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: DoorDto
 * @description:
 * @author: fzy
 * @date: 2019/05/20 15:03:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoorDto implements Serializable{

    private Long id;

    private String username;

    private DoorStatus doorStatus;

    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    private Date expireDate;

    private DoorType doorType;
}
