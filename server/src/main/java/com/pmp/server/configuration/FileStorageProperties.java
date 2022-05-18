package com.pmp.server.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return System.getProperty("user.dir") + "/static";
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}