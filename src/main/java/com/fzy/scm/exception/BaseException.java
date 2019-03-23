package com.fzy.scm.exception;

import com.fzy.scm.entity.enums.RestCode;
import lombok.Getter;

/**
 * @program: BaseException
 * @description:
 * @author: fzy
 * @date: 2019/03/23 18:53:36
 **/
@Getter
public class BaseException extends RuntimeException{
    protected Integer code;

    BaseException(String message) {
      super(message);
    }

    BaseException(RestCode restCode){
        super(restCode.getMessage());
        this.code = restCode.getCode();
    }

}
