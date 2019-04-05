package com.fzy.pms.entity.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fzy.pms.entity.enums.RestCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: Result
 * @description:
 * @author: fzy
 * @date: 2019/03/17 08:56:50
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("统一返回结果实体")
public class Result<T> {

    @ApiModelProperty(value = "返回代码",example="200")
    private Integer code;

    @ApiModelProperty("失败消息")
    private String message;

    @ApiModelProperty("结果对象")
    private T data;

    @ApiModelProperty("时间戳")
    private long timestamp = System.currentTimeMillis();

    public static Result success(){
        Result result=new Result();
        result.setRestCode(RestCode.SUCCESS);
        return result;
    }

    public static<T> Result success(T data){
        Result<T> result=new Result<>();
        result.setRestCode(RestCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(RestCode code){
        Result result=new Result();
        result.setRestCode(code);
        return result;
    }


    public static Result failure(String message){
        Result result=new Result();
        result.setCode(RestCode.SYS_ERROR_EXCEPTION.getCode());
        result.setMessage(message);
        return result;
    }


    public static Result failure(Integer code, String message){
        Result result=new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static<T> Result failure(Integer code,T data){
        Result<T> result=new Result<>();
        result.setCode(code);
        result.setData(data);
        return result;
    }

    private void setRestCode(RestCode restCode){
        this.code=restCode.getCode();
        this.message=restCode.getMessage();
    }

}
