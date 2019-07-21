package com.fzy.pms.service;

import com.qiniu.common.QiniuException;

/**
 * @program: uploadFileService
 * @description: 文件上传服务
 * @author: fzy
 * @date: 2019/07/19 23:38:47
 **/
public interface UploadFileService {

    /**
     * 图片上传
     */
    void uploadImage();

    /**
     * 本地文件上传
     */
    void localUpload(String filePath, String fileName) throws QiniuException;
}
