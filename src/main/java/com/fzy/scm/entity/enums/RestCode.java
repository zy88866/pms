package com.fzy.scm.entity.enums;

import lombok.Getter;

/**
 * @program: RestCode
 * @description: 全局错误码
 * @author: fzy
 * @date: 2019/03/17 09:00:51
 **/
@Getter
public enum RestCode {
    SUCCESS(200,"SUCCESS"),
    FAILURE(500,"FAILURE"),
    PARAM_ERROR(400,"参数错误"),
    NO_USER_ERROR(501,"用户不存在");

    private Integer code;

    private String message;

    RestCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
