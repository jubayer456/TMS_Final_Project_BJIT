package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.CourseRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/api/course/save")
    public ResponseEntity<Object> createCourse(@RequestBody CourseReqDto courseReqDto){
        return courseService.createCourse(courseReqDto);
    }
    @PutMapping("/api/course/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable Long courseId,@RequestBody CourseReqDto courseReqDto){
        return courseService.updateCourse(courseId,courseReqDto);
    }

    @GetMapping("/api/course/{courseId}")
    public ResponseEntity<Object> getCourseDetails(@PathVariable Long courseId){
        return courseService.getCourseDetails(courseId);
    }

}
