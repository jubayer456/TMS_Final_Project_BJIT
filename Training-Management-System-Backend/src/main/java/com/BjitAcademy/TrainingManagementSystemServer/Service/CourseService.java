package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface CourseService {


    ResponseEntity<Object> createCourse(CourseReqDto courseReqDto);

    ResponseEntity<Object> updateCourse(Long courseId,CourseReqDto courseReqDto);

    ResponseEntity<Object> getCourseDetails(Long courseId);

    ResponseEntity<List<CourseResDto>> getAllCourse();

    ResponseEntity<Object> deleteCourse(Long courseId);
}
