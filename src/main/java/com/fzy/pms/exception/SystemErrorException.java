package com.fzy.pms.exception;

import com.fzy.pms.entity.enums.RestCode;

import java.util.Optional;

/**
 * @program: SystemErrorException
 * @description: 异常内部异常
 * @author: fzy
 * @date: 2019/03/17 09:01:29
 **/
public class SystemErrorException extends BaseException {

    public SystemErrorException(String message) {
        super(Optional.ofNullable(message).orElse(RestCode.SYS_ERROR_EXCEPTION.getMessage()));
        this.code = RestCode.SYS_ERROR_EXCEPTION.getCode();
    }
}
