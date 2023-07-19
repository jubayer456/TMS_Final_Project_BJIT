package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.ClassRoom.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;
    @PostMapping("/api/classroom/add-post")
    public ResponseEntity<String> addPost(@RequestBody ClassRoomPostReqDto post){
        return classroomService.addPost(post);
    }
}
