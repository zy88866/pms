package com.fzy.pms.web.controller;

import com.fzy.pms.entity.dto.AccountDto;
import com.fzy.pms.entity.pms.AccountDetail;
import com.fzy.pms.entity.pms.House;
import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.AccountService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @program: AccountController
 * @description:
 * @author: fzy
 * @date: 2019/05/21 22:09:49
 **/
@RestController
@RequestMapping("/account")
@Api(value = "账户接口",description = "账户相关的接口")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    @ApiOperation(value = "查询全部账户余额",notes = "查询全部账户余额")
    public Result getAllAccount(@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        Page<AccountDto> allData = accountService.findAll(pageable);
        return Result.success(allData);
    }

    @GetMapping("/search")
    @ApiOperation(value = "查询全部账户余额",notes = "根据id查询全部账户余额")
    public Result search(@RequestParam("userId") String userId,@PageableDefault(sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        if(StringUtils.isBlank(userId) ||"null".equals(userId) ){
            return this.getAllAccount(pageable);
        }else {
            return Result.success(accountService.search(Long.parseLong(userId),pageable));
        }
    }

    @PostMapping
    @ApiOperation(value = "账户充值",notes = "账户充值")
    public Result payment(@RequestBody AccountDetail accountDetail){
        accountService.payment(accountDetail);
        return Result.success();
    }

    @GetMapping("/report")
    @ApiOperation(value = "查询充值报表",notes = "查询充值报表")
    public Result report(@RequestParam("userId") String userId){
        return Result.success(accountService.report(userId));
    }

}
