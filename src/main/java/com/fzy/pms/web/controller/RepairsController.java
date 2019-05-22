package com.fzy.pms.web.controller;

import com.fzy.pms.entity.pms.Park;
import com.fzy.pms.entity.pms.Repairs;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.RepairsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: RepairsController
 * @description:
 * @author: fzy
 * @date: 2019/05/22 19:57:28
 **/
@RestController
@RequestMapping("/repairs")
@Api(value = "报修接口",description = "报修相关的接口")
public class RepairsController {

    @Autowired
    private RepairsService repairsService;

    @PostMapping
    @ApiOperation(value = "添加报修单",notes = "添加报修单")
    public Result create(@Validated(Park.Save.class) @RequestBody Repairs repairs){
        repairsService.create(repairs);
        return Result.success();
    }
}
