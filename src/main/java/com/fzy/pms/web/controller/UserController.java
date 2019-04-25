package com.fzy.pms.web.controller;

import com.fzy.pms.dao.UserRepository;
import com.fzy.pms.entity.dto.UserDto;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.entity.security.User;
import com.fzy.pms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Api(value = "用户接口",description = "用户相关的接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "查询用户",notes = "根据用户id查询用户信息")
    public Result getUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return user.isPresent()? Result.success(user.get()):Result.failure("获取用户信息失败");
    }

    @ApiOperation(value="添加用户", notes="用户实体")
    @PostMapping
    public Result createUser(@Valid @RequestBody User user){
        return Objects.isNull(userService.registerUser(user))?Result.failure("添加用户失败"): Result.success();
    }

    @ApiOperation( "获取当前登录用户信息")
    @GetMapping("/info")
    public Result getUserInfo(){
        Optional<UserDto> currUserInfo = userService.getCurrUserInfo();
        return currUserInfo.isPresent()? Result.success(currUserInfo.get()):Result.failure("获取用户信息失败");
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "删除用户",notes = "根据id 删除用户")
    public Result deleteUser(@PathVariable Long id){
        userService.lockUser(id);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询当前用户列表", notes = "查询当前用户列表")
    public Result getUserList(){
        List<UserDto> List = userService.findAllListSortCreateTime();
        return Result.success(List);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑用户信息", notes="编辑用户信息")
    public Result editUserInfo(@Validated(User.Update.class) @RequestBody User user){
        userService.updateUserInfo(user);
        return Result.success();
    }

}
