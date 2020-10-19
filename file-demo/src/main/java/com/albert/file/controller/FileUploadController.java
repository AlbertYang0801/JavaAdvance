package com.albert.file.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 * @author Albert
 * @date 2020/9/25 10:31
 */
@Slf4j
@RestController
public class FileUploadController {

    /**
     * 文件上传路径
     */
    @Value("${file.upload.path}")
    private String uploadPath;

    /**
     * Content-type为multipart/form-data类型的文件上传
     * 表单提交
     * @param multipartFile
     * @return
     */
    @PostMapping("/file/upload")
    public String uploadBillClient(@RequestParam("fileName") MultipartFile multipartFile) {
        log.info("上传的文件名称：{}", multipartFile.getOriginalFilename());
        log.info("上传的文件大小：{}", multipartFile.getSize());
        String path = uploadPath + multipartFile.getOriginalFilename();
        log.info("文件将要上传的路径为:{}", path);
        File file = new File(path);
        //如果上级目录不存在，则创建上级目录
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            //覆盖指定文件
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发送成功";
    }


}
