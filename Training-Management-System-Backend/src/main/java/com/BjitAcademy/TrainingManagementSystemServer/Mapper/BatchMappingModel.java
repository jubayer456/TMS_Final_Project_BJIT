package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Course.CourseResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.BatchScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BatchMappingModel {
    public static BatchEntity BatchDtoToEntity(BatchReqDto batchReqDto, ClassRoom classRoom){
        return BatchEntity.builder()
                .batchId(classRoom.getClassRoomId())
                .batchName(classRoom.getClassRoomName())
                .startingDate(batchReqDto.getStartingDate())
                .endingDate(batchReqDto.getEndingDate())
                .totalNumOfTrainee(batchReqDto.getTotalNumOfTrainee())
                .classRoom(classRoom)
                .build();
    }
    public static BatchResDto BatchEntityToDto(BatchEntity batchEntity){
        return BatchResDto.builder()
                .batchId(batchEntity.getBatchId())
                .batchName(batchEntity.getBatchName())
                .startingDate(batchEntity.getStartingDate())
                .endingDate(batchEntity.getEndingDate())
                .totalNumOfTrainee(batchEntity.getTotalNumOfTrainee())
                .classRoomName(batchEntity.getClassRoom().getClassRoomName())
                .build();
    }

    public static BatchScheduleResDto scheduleEntityToBatchRes(ScheduleEntity schedule, CourseEntity course, Long batchId){
        return BatchScheduleResDto.builder()
                .scheduleId(schedule.getScheduleId())
                .batchId(batchId)
                .courseId(course.getCourseId())
                .startingDate(schedule.getStartingDate())
                .endingDate(schedule.getEndingDate())
                .courseName(course.getName())
                .profilePicture(course.getTrainer().getUser().getProfilePicture())
                .trainerName(course.getTrainer().getUser().getFullName())
                .trainerId(course.getTrainer().getTrainerId())
                .build();
    }
}
