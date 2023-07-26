package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.CourseEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ScheduleEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TrainerEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.CourseException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.ScheduleException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TrainerException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.CourseMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.CourseRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.ScheduleRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.TrainerRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ResponseEntity<Object> createCourse(CourseReqDto courseReqDto) {
        TrainerEntity trainer=trainerRepository.findByTrainerId(courseReqDto.getTrainerId());
        if (trainer==null){
            throw new TrainerException("Trainer not found for Course");
        }
        CourseEntity course= CourseMappingModel.CourseDtoToEntity(courseReqDto,trainer);
        courseRepository.save(course);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("Successfully Registered Course")
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateCourse(Long courseId, CourseReqDto courseReqDto) {
       CourseEntity course=courseRepository.findByCourseId(courseId);
       if(course==null){
           throw new CourseException("course not found for update");
       }
       TrainerEntity trainer=trainerRepository.findByTrainerId(courseReqDto.getTrainerId());
       if (trainer==null){
           throw new TrainerException("please enter valid trainerId");
       }
       course.setName(courseReqDto.getName());
       course.setTrainer(trainer);
       courseRepository.save(course);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("Successfully update Course")
                .build();
       return new ResponseEntity<>(success,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getCourseDetails(Long courseId) {
        CourseEntity course=courseRepository.findByCourseId(courseId);
        if(course==null){
            throw new CourseException("course not found for update");
        }
        CourseResDto courseResDto=CourseMappingModel.CourseEntityToDto(course);
        return new ResponseEntity<>(courseResDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CourseResDto>> getAllCourse() {
        List<CourseEntity> courses=courseRepository.findAll();
        List<CourseResDto> courseResList=courses.stream().map(CourseMappingModel::CourseEntityToDto).toList();
        return new ResponseEntity<>(courseResList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> deleteCourse(Long courseId) {
        List<ScheduleEntity> schedules=scheduleRepository.findAll();
        if(schedules.stream().anyMatch(scheduleEntity -> Objects.equals(scheduleEntity.getCourseId(), courseId))){
            throw new ScheduleException("Schedule already exist so you can not delete");
        }
        CourseEntity course=courseRepository.findByCourseId(courseId);
        if (course==null){
            throw new CourseException("Course id is invalid,,, please give a valid course Id");
        }
        course.setTrainer(null);
        courseRepository.delete(course);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("SuccessFully deleted course id: "+courseId)
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
}
