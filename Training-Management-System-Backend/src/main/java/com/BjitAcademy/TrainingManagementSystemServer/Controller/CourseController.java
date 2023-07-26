package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/api/course-save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createCourse(@RequestBody CourseReqDto courseReqDto){
        return courseService.createCourse(courseReqDto);
    }
    @PutMapping("/api/courses/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateCourse(@PathVariable Long courseId,@RequestBody CourseReqDto courseReqDto){
        return courseService.updateCourse(courseId,courseReqDto);
    }

    @GetMapping("/api/courses/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getCourseDetails(@PathVariable Long courseId){
        return courseService.getCourseDetails(courseId);
    }

    @GetMapping("/api/courses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CourseResDto>> getAllCourse(){
        return courseService.getAllCourse();
    }
    @DeleteMapping("/api/courses/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long courseId){
        return courseService.deleteCourse(courseId);
    }
}
