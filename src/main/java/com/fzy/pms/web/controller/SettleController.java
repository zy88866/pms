package com.fzy.pms.web.controller;

import com.fzy.pms.entity.pms.Settle;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.SettleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SettleController
 * @description:
 * @author: fzy
 * @date: 2019/05/23 08:34:08
 **/

@RestController
@RequestMapping("/settle")
@Api(value = "结算接口",description = "结算相关的接口")
public class SettleController {

    @Autowired
    private SettleService settleService;

    @PostMapping
    @ApiOperation("创建结算")
    public Result create(@RequestBody Settle settle){
        settleService.create(settle);
        return Result.success();
    }

}
