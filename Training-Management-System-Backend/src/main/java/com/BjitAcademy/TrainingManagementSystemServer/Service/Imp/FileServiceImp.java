package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;


import com.BjitAcademy.TrainingManagementSystemServer.Entity.FileDataEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.FileRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImp implements FileService {
    private final FileRepository fileRepository;
    @Override
    public ResponseEntity<Object> uploadImageToFileSystem(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Optional<FileDataEntity> fileData = fileRepository.findByName(fileName);
        if (fileData.isPresent()){
            throw new FileAlreadyExistsException("File is already exist in storage");
        }

        Path currentDir = Paths.get("").toAbsolutePath();
        // Specify the directory where you want to store the file
        File dir = new File(currentDir + File.separator+"file-upload" );
        // Create the directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = dir.getAbsolutePath() + File.separator + fileName;
        file.transferTo(new File(filePath));
        FileDataEntity fileDataEntity=FileDataEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build();
        fileRepository.save(fileDataEntity);
        return new ResponseEntity<>(fileDataEntity,HttpStatus.OK);
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        takes the fileName as input and returns a byte array (byte[])
        Optional<FileDataEntity> fileData = fileRepository.findByName(fileName);
//        findByName that searches for a record in the database based on the given fileName.
        String filePath=fileData.get().getFilePath();
//        this line is parsing the type property from the FileDataEntity object into a MediaType object
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
