package com.fzy.pms.web.controller;

import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.entity.security.Menu;
import com.fzy.pms.entity.security.Role;
import com.fzy.pms.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: RoleController
 * @description:
 * @author: fzy
 * @date: 2019-04-05 11:12
 **/
@RestController
@RequestMapping("/role")
@Api(value = "角色接口",description = "角色相关的接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @ApiOperation(value = "添加角色",notes = "添加角色")
    public Result create(@Validated @RequestBody Role role){
        Role roleVo = roleService.create(role);
        return Result.success(roleVo);
    }

    @PutMapping
    @ApiOperation(value = "修改角色",notes = "修改角色")
    public Result update(@Validated(Menu.Update.class) @RequestBody Role role){
        roleService.update(role);
        return Result.success();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "删除角色",notes = "根据id 删除角色")
    public Result deleteRole(@PathVariable Long id){
        roleService.delete(id);
        return Result.success();
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询全部角色",notes = "查询全部角色")
    public Result findAll(){
        return Result.success(roleService.findAll());
    }


}
