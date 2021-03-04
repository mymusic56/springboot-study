package com.mymusic.testupload.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangshangji
 * @since 2021/2/7 15:32
 */
@Component
@Data
public class UploadsConfig {
    @Value("${spring.customConfig.uploadFile.uploadPath}")
    private String uploadsDir;
}
