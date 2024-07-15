package com.hugodev.upload_arquivos_api.controller;

import com.hugodev.upload_arquivos_api.service.UploadService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "/upload")
public class UploadArquivoController {

    @Autowired
    UploadService uploadService;

    @PostMapping
    public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file)  {
         return this.uploadService.uploadFile(file);
    }



}
