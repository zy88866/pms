package com.fzy.pms.web.controller;

import com.fzy.pms.entity.dto.DoorDto;
import com.fzy.pms.entity.pms.Door;
import com.fzy.pms.entity.pms.House;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.DoorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: DoorController
 * @description: 门禁管理
 * @author: fzy
 * @date: 2019/05/20 09:57:15
 **/
@RestController
@RequestMapping("/door")
@Api(value = "门禁管理",description = "门禁管理相关的接口")
public class DoorController {

    @Autowired
    private DoorService doorService;

    @PostMapping
    @ApiOperation(value = "添加门禁",notes = "添加门禁")
    public Result create(@Validated(Door.Save.class) @RequestBody Door door){
        doorService.create(door);
        return Result.success();
    }

    @GetMapping("/all")
    @ApiOperation(value = "查询全部门禁",notes = "查询全部门禁")
    public Result getAllDoor(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        Page<DoorDto> all = doorService.findAllDto(pageable);
        return Result.success(all);
    }

    @GetMapping("/search")
    @ApiOperation(value = "根据用户id 查询全部门禁",notes = "根据用户id 查询全部门禁")
    public Result search(@RequestParam("userId") String userId,@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        if(!userId.equals("null")){
            return Result.success(doorService.search(Long.parseLong(userId),pageable));
        }else {
            return this.getAllDoor(pageable);
        }
    }

    @PutMapping("/updateStatus")
    @ApiOperation(value = "修改门禁状态",notes = "根据id修改门禁状态")
    public Result updateStatus(@RequestBody Door door){
        return Result.success(doorService.updateDoorStatus(door));
    }


}
