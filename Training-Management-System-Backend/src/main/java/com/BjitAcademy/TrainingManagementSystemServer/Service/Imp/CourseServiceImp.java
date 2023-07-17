package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.CourseEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ScheduleEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TrainerEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.CourseNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.ScheduleAlreadyExistException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TraineeNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TrainerNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.AdminMappingModel;
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
            throw new TrainerNotFoundException("Trainer not found for Course");
        }
        CourseEntity course= CourseMappingModel.CourseDtoToEntity(courseReqDto,trainer);
        courseRepository.save(course);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("Successfully Registered Course")
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }

}
