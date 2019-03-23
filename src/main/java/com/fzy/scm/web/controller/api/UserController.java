package com.fzy.scm.web.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.fzy.scm.dao.UserRepository;
import com.fzy.scm.entity.enums.RestCode;
import com.fzy.scm.entity.rest.Result;
import com.fzy.scm.entity.security.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "用户接口",description = "用户相关的接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUser(@PageableDefault(page = 1,size = 20,sort = "userName desc") Pageable pageable){
        System.out.println(pageable.getPageNumber());
        return null;
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "查询用户",notes = "根据用户id查询用户信息")
    public Result getUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        return user.isPresent()? Result.success(user.get()):Result.failure(RestCode.NO_USER_ERROR);
    }

    @ApiOperation(value="添加用户", notes="传入用户实体")
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }


    @ApiOperation( "获取当前登录用户信息")
    @GetMapping("/info")
    public Result getUserInfo(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
        return dbUser.isPresent()? Result.success(dbUser.get()):Result.failure(RestCode.NO_USER_ERROR);
    }

}
