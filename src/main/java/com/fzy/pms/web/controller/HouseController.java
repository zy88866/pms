package com.fzy.pms.web.controller;

import com.fzy.pms.entity.pms.CostSetting;
import com.fzy.pms.entity.pms.House;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @program: HouseController
 * @description:
 * @author: fzy
 * @date: 2019/05/10 10:03:49
 **/
@RestController
@RequestMapping("/house")
@Api(value = "房产接口",description = "房产相关的接口")
public class HouseController  {

    @Autowired
    private HouseService houseService;

    @GetMapping("/all")
    @ApiOperation(value = "查询全部房产",notes = "查询全部房产")
    public Result getHouse(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        Page<House> allData = houseService.findAll(pageable);
        return Result.success(allData);
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "查找房产",notes = "根据 id 查找房产")
    public Result findOne(@PathVariable Long id){
        House house = houseService.findOne(id);
        return Result.success(house);
    }

    @PostMapping
    @ApiOperation(value = "添加房产",notes = "添加房产")
    public Result create(@Validated(House.Save.class) @RequestBody House house){
        houseService.create(house);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改房产",notes = "修改房产")
    public Result updateCost(@Validated(CostSetting.Update.class) @RequestBody House house){
        houseService.update(house);
        return Result.success();
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation(value = "删除房产",notes = "根据id 删除房产")
    public Result deleteHouse(@PathVariable Long id){
        houseService.delete(id);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除房产",notes = "批量删除房产信息")
    public Result batchDelete(@RequestBody Set<Long> ids){
        houseService.batchDelete(ids);
        return Result.success();
    }

    @GetMapping("/search")
    public Result search(@RequestParam("userId")Long id,@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        Page<House> house = houseService.findHouseByUserId(id,pageable);
        return Result.success(house);
    }
}
