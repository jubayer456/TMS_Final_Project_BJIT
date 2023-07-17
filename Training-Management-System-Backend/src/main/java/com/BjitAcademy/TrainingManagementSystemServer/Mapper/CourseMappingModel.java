package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.CourseEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TrainerEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CourseMappingModel {
    public static CourseEntity CourseDtoToEntity(CourseReqDto courseReqDto, TrainerEntity trainer){
        return CourseEntity.builder()
                .name(courseReqDto.getName())
                .trainer(trainer)
                .build();
    }  public static CourseResDto CourseEntityToDto(CourseEntity courseEntity){
        return CourseResDto.builder()
                .courseId(courseEntity.getCourseId())
                .name(courseEntity.getName())
                .trainerId(courseEntity.getTrainer().getTrainerId())
                .trainerName(courseEntity.getTrainer().getUser().getFullName())
                .profilePicture(courseEntity.getTrainer().getUser().getProfilePicture())
                .build();
    }
}
