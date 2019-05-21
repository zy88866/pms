package com.fzy.pms.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fzy.pms.entity.enums.DoorType;
import com.fzy.pms.entity.enums.ParkType;
import com.fzy.pms.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @program: ParkDto
 * @description:
 * @author: fzy
 * @date: 2019/05/20 23:16:21
 **/
@Data
@AllArgsConstructor
public class ParkDto {

    private Long id;

    private String username;

    private UserStatus userStatus;

    @JsonFormat(pattern="yyyy-MM-dd",locale = "zh", timezone = "GMT+8")
    private Date expireDate;

    private ParkType parkType;

    private String position;
}
