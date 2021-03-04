package com.mymusic.testupload.controller;

import com.mymusic.testupload.config.UploadsConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshangji
 * @since 2021/2/7 15:13
 */
@RestController
@RequestMapping("/uploads")
public class UploadsController {

    @Resource
    UploadsConfig uploadsConfig;

    @RequestMapping("/getUploadDir")
    public String getUploadDir() {
        return uploadsConfig.getUploadsDir();
    }

    @PostMapping("/singleFileUpload")
    public Map<String, Object> singleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        if (file.isEmpty()) {
            result.put("error", 1);
            result.put("msg", "文件不能为空");
            return result;
        }
        if (uploadsConfig.getUploadsDir() == null) {
            result.put("error", 1);
            result.put("msg", "文件上传目录未配置！");
            return result;
        }

        String name = file.getName();
        String newFilePath = uploadsConfig.getUploadsDir() + file.getOriginalFilename();
        File newFile = new File(newFilePath);
        result.put("error", 0);
        result.put("msg", "");
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            result.put("error", 500);
            result.put("msg", e.getMessage());
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return result;
    }
}
