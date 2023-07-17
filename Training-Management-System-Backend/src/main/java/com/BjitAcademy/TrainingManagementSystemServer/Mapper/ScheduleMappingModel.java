package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.CourseEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ScheduleEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleMappingModel {
    public static ScheduleEntity scheduleDtoToEntity(ScheduleReqDto scheduleReqDto,CourseEntity course){
        return ScheduleEntity.builder()
                .courseId(scheduleReqDto.getCourseId())
                .batchId(scheduleReqDto.getBatchId())
                .startingDate(scheduleReqDto.getStartingDate())
                .endingDate(scheduleReqDto.getEndingDate())
                .trainerId(course.getTrainer().getTrainerId())
                .build();
    }
    public static ScheduleResDto scheduleEntityToDto(ScheduleEntity scheduleEntity){
        return ScheduleResDto.builder()
                .scheduleId(scheduleEntity.getScheduleId())
                .courseId(scheduleEntity.getCourseId())
                .startingDate(scheduleEntity.getStartingDate())
                .endingDate(scheduleEntity.getEndingDate())
                .batchId(scheduleEntity.getBatchId())
                .trainerId(scheduleEntity.getTrainerId())
                .build();
    }
}
