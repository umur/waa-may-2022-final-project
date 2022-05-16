package com.pmp.server.controller;

import com.pmp.server.dto.UploadFileResponseDTO;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
@CrossOrigin
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    private final FileStorageService fileStorageService;


    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileDownloadUri = fileStorageService.storeFile(file);

        UploadFileResponseDTO response = new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());

        return ResponseEntity.ok(new ResponseMessage("Success", HttpStatus.CREATED, response));
    }

    @PostMapping("/upload-multiple-files")
    public ResponseEntity<ResponseMessage> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        var response = Arrays.asList(files)
                .stream()
                .map(file -> {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    String fileDownloadUri = fileStorageService.storeFile(file);
                    UploadFileResponseDTO fileResponse = new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
                    return fileResponse;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseMessage("Success", HttpStatus.CREATED, response));
    }
}
