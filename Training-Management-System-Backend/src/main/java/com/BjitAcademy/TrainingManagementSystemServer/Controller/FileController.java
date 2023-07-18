package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Entity.FileDataEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.FileRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final FileRepository fileRepository;

    @PostMapping("/api/upload")
    public  ResponseEntity<Object> uploadImageToFIleSystem(@RequestParam("file")MultipartFile file) throws IOException {
        return fileService.uploadImageToFileSystem(file);
    }

    @GetMapping("/api/download/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=fileService.downloadImageFromFileSystem(fileName);
        Optional<FileDataEntity> attachment=fileRepository.findByName(fileName);
        MediaType type=(MediaType.parseMediaType(attachment.get().getType()));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(type)
                .body(imageData);
    }

//    @GetMapping("/api/download/{fileName}")
//        public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//            try {
//                // Load the file as a resource
//                Path filePath = Paths.get("file-upload").resolve(fileName).normalize();
//                Resource resource = new UrlResource(filePath.toUri());
//
//                // Check if the file exists
//                if (!resource.exists()) {
//                    throw new FileNotFoundException("File not found: " + fileName);
//                }
//
//                // Determine the file's content type
//                String contentType = Files.probeContentType(filePath);
//
//                // Set the response headers
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.parseMediaType(contentType));
//                headers.setContentDispositionFormData("attachment", fileName);
//
//                // Create the response entity
//                return ResponseEntity.ok()
//                        .headers(headers)
//                        .body(resource);
//            } catch (MalformedURLException e) {
//                return ResponseEntity.notFound().build();
//            } catch (IOException e) {
//                return ResponseEntity.badRequest().build();
//            }
//        }
}
