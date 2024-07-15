package com.hugodev.upload_arquivos_api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {

    private final String path;

    public UploadService(@Value("${app.path.files}") String filePath) {
        this.path = filePath;
    }

    public ResponseEntity<String> uploadFile(MultipartFile file){
        var caminho = path + UUID.randomUUID() + "." + extrairExtensao(file.getOriginalFilename());
        try{
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("Saved file.");
        }catch (Exception e){
            log.error("Erro ao processar arquivo"+ e.getMessage());
            return ResponseEntity.internalServerError().body("Error processing file.");
        }
    }

    private String extrairExtensao(String nameFile) {
        int i = nameFile.lastIndexOf(".");
        return nameFile.substring(i + 1);
    }
}
