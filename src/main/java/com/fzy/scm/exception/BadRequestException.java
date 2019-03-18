package com.fzy.scm.exception;

import com.fzy.scm.entity.enums.RestCode;
import lombok.Data;

import java.util.Optional;

/**
 * @program: BadRequestException
 * @description: 参数异常
 * @author: fzy
 * @date: 2019/03/17 09:01:29
 **/
@Data
public class BadRequestException extends RuntimeException {
    private Integer code;

    public BadRequestException(String message) {
        super(Optional.ofNullable(message).orElse(RestCode.PARAM_ERROR.getMessage()));
        this.code = RestCode.PARAM_ERROR.getCode();
    }

    public BadRequestException(RestCode restCode) {
        super(restCode.getMessage());
        this.code = restCode.getCode();
    }
}
