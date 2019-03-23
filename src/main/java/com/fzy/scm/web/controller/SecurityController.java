package com.fzy.scm.web.controller;

import com.fzy.scm.entity.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SecurityController
 * @description:
 * @author: fzy
 * @date: 2019/03/23 20:37:05
 **/
@Slf4j
@RestController
@Api(description = "Security相关接口")
@RequestMapping("/common")
public class SecurityController {

    @GetMapping("/needLogin")
    @ApiOperation(value = "没有登录")
    public Result needLogin(){
        return Result.failure("");
    }
}
