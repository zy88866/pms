package com.fzy.pms.service.impl;

import com.fzy.pms.service.DownloadFileService;
import com.fzy.pms.service.UploadFileService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: UploadFileServiceImpl
 * @description:
 * @author: fzy
 * @date: 2019/07/19 23:40:03
 **/
@Service("fileService")
@Slf4j
public class FileServiceImpl implements UploadFileService, DownloadFileService {

    @Value("${qiniu.oos.AccessKey}")
    private String AccessKey;

    @Value("${qiniu.oos.SecretKey}")
    private String SecretKey;

    @Value("${qiniu.oos.bucket}")
    private String bucket;

    @Value("${qiniu.oos.domain}")
    private String domain;

    private Auth createAuth(){
        Auth auth = Auth.create(AccessKey, SecretKey);
        return auth;
    }

    private String getAccessToken(){
        Auth auth = this.createAuth();
        return auth.uploadToken(bucket);
    }
    @Override
    public void uploadImage() {
        String token = this.getAccessToken();
        log.info(token);
    }

    @Override
    public void localUpload(String filePath, String fileName) throws QiniuException {
        Configuration cfg=new Configuration(Zone.zone0());
        UploadManager uploadManager=new UploadManager(cfg);
        String accessToken = this.getAccessToken();
        Response response = uploadManager.put(filePath, fileName, accessToken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.debug("upload success key: {} , value: {} ",defaultPutRet.key,defaultPutRet.hash);
}

    @Override
    public File downloadFile(String fileName) {
        Auth auth = this.createAuth();
        try {
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            String downUrl = String.format("%s/%s", domain, encodedFileName);
            String downloadUrl = auth.privateDownloadUrl(downUrl,60);
            log.info(downloadUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
