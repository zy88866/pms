package com.fzy.pms.web.controller;

import com.fzy.pms.entity.rest.Result;
import com.fzy.pms.service.UploadFileService;
import com.qiniu.common.QiniuException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: UploadController
 * @description:
 * @author: fzy
 * @date: 2019/07/19 23:56:02
 **/
@Api(value = "文件上传接口",description = "文件上传相关的接口")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping
    @ApiOperation(value = "文件上传",notes = "文件上传")
    public Result uploadImages()throws QiniuException {
        uploadFileService.localUpload("C:\\GraduateProject\\pms\\src\\main\\resources\\banner.txt","banner.txt");
        return Result.success();
    }
}
