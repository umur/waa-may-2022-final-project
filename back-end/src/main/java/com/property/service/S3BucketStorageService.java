package com.property.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.property.dto.PhotoMeta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3BucketStorageService {

    private final AmazonS3 s3client;

    @Value("${application.bucket.name}")
    private String bucketName;

    public List<PhotoMeta> uploadFiles(String prefix, List<MultipartFile> files) {
        log.info("Uploading images of size {}", files.size());
        List<PhotoMeta> photoMetas = new ArrayList<>();
        for(MultipartFile file: files){
            try {
                String key = prefix+ File.separator+ file.getName();
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                s3client.putObject(bucketName,key , file.getInputStream(), metadata);
                photoMetas.add(new PhotoMeta(s3client.getUrl(bucketName, key).toString(), key));
            }  catch (AmazonServiceException serviceException) {
                log.info("AmazonServiceException: "+ serviceException.getMessage());
                throw serviceException;
            } catch (AmazonClientException clientException) {
                log.info("AmazonClientException Message: " + clientException.getMessage());
                throw clientException;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return photoMetas;
    }


}
