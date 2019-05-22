package com.fzy.pms.web.controller;

import com.fzy.pms.entity.pms.Park;
import com.fzy.pms.entity.pms.Repairs;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.entity.vo.RepairVo;
import com.fzy.pms.service.RepairsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ApiOperation(value = "查询全部报修单",notes = "查询全部报修单")
    public Result findAllDto(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return Result.success(repairsService.findAllDto(pageable));
    }

    @GetMapping("/search")
    @ApiOperation(value = "查询全部报修单",notes = "查询全部报修单")
    public Result search(@RequestParam ()String userId, @PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        if(StringUtils.isBlank(userId)||userId.equals("null")){
            return this.findAllDto(pageable);
        }else {
            return Result.success(repairsService.search(Long.parseLong(userId),pageable));
        }
    }

    @PutMapping("/startDispatch")
    @ApiOperation(value = "开始派单",notes = "开始派单")
    public Result startDispatch(@RequestBody RepairVo repairVo){
        repairsService.startDispatch(repairVo);
        return Result.success();
    }

    @PutMapping("/endDispatch")
    @ApiOperation(value = "关闭订单",notes = "关闭订单")
    public Result endDispatch(@RequestBody RepairVo repairVo){
        repairsService.endDispatch(repairVo);
        return Result.success();
    }


}
