package com.BjitAcademy.TrainingManagementSystemServer.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    ResponseEntity<Object> uploadImageToFileSystem(MultipartFile file) throws IOException;

    byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
