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
//    @Override
//    public ResponseEntity<Object> fileUpload(MultipartFile file) {
//        try {
//            // Get the file name
//            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//            AssignmentEntity assignment=assignmentRepository.findByAssignmentFile(fileName);
//            AssignmentSubEntity assignmentSub=assignmentSubRepository.findBySubmissionFile(fileName);
//            UserEntity user= userRepository.findByProfilePicture(fileName);
//            if(assignment!=null || assignmentSub!=null || user!=null){
//                return new ResponseEntity<>("give unique file name,, this file is already presented in uploaded folder", HttpStatus.NOT_ACCEPTABLE);
//            }
//            Path currentDir = Paths.get("").toAbsolutePath();
//            // Specify the directory where you want to store the file
//            File dir = new File(currentDir + File.separator+"file-upload" );
//            // Create the directory if it doesn't exist
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            // Create the file path
//            String filePath = dir.getAbsolutePath() + File.separator + fileName;
//            // Save the file
//            file.transferTo(new File(filePath));
//            return new ResponseEntity<>(fileName, HttpStatus.OK);
//        }
//        catch (FileSizeLimitExceededException e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>("please upload 1MB file size: ",HttpStatus.NOT_ACCEPTABLE);
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>("Failed to upload file: "+ e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Resource> downloadFiles(String fileName) {
//        try {
//            // Load the file as a resource
//            Path filePath = Paths.get("file-upload").resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            // Check if the file exists
//            if (!resource.exists()) {
//                throw new FileNotFoundException("File not found: " + fileName);
//            }
//
//            // Determine the file's content type
//            String contentType = Files.probeContentType(filePath);
//
//            // Set the response headers
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(contentType));
//            headers.setContentDispositionFormData("attachment", fileName);
//
//            // Create the response entity
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(resource);
//        } catch (MalformedURLException e) {
//            return ResponseEntity.notFound().build();
//        } catch (IOException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

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
        Optional<FileDataEntity> fileData = fileRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
