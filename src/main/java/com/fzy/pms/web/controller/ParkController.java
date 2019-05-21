package com.fzy.pms.web.controller;

import com.fzy.pms.entity.dto.ParkDto;
import com.fzy.pms.entity.pms.Park;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.ParkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: ParkController
 * @description:
 * @author: fzy
 * @date: 2019/05/20 19:07:03
 **/
@RestController
@RequestMapping("/park")
@Api(value = "停车位接口",description = "停车位相关的接口")
public class ParkController {

    @Autowired
    private ParkService parkService;

    @PostMapping
    @ApiOperation(value = "添加停车位",notes = "添加停车位")
    public Result create(@Validated(Park.Save.class) @RequestBody Park park){
        parkService.create(park);
        return Result.success();
    }

    @GetMapping("/all")
    @ApiOperation(value = "查询全部停车位",notes = "查询全部停车位")
    public Result getAllPark(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        Page<ParkDto> all = parkService.findAllDto(pageable);
        return Result.success(all);
    }

    @GetMapping("/search")
    @ApiOperation(value = "gen",notes = "查询全部门禁")
    public Result search(@RequestParam ()String userId, @PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        if(!userId.equals("null")){
            return Result.success(parkService.search(Long.parseLong(userId),pageable));
        }else {
            return this.getAllPark(pageable);
        }
    }

    @PutMapping("/updateStatus")
    @ApiOperation(value = "修改停车位状态",notes = "根据id修改停车位状态")
    public Result updateStatus(@RequestBody Park park){
        return Result.success(parkService.updateUseStatus(park));
    }

}
