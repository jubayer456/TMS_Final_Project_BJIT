package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.AsignSubReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.AsignSubResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.AssignmentReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.AssignmentResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.AssignmentEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.AssignmentSubEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.ScheduleEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TraineeEntity;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class AssignmentMappingModel {
    public static AssignmentEntity assignmentDtoToEntity(AssignmentReqDto assignmentReqDto){
        return AssignmentEntity.builder()
                .scheduleId(assignmentReqDto.getScheduleId())
                .assignmentName(assignmentReqDto.getAssignmentName())
                .assignmentFile(assignmentReqDto.getAssignmentFile())
                .deadLine(assignmentReqDto.getDeadLine())
                .build();
    }
    public static AssignmentResDto assignmentEntityToDto(AssignmentEntity assignmentEntity){
        return AssignmentResDto.builder()
                .assignmentId(assignmentEntity.getAssignmentId())
                .batchId(assignmentEntity.getBatchId())
                .scheduleId(assignmentEntity.getScheduleId())
                .assignmentName(assignmentEntity.getAssignmentName())
                .assignmentFile(assignmentEntity.getAssignmentFile())
                .deadLine(assignmentEntity.getDeadLine())
                .build();
    }
    public static AssignmentSubEntity assignmentSubDtoToEntity(AsignSubReqDto asignSubReqDto){
        return AssignmentSubEntity.builder()
                .assignmentId(asignSubReqDto.getAssignmentId())
                .traineeId(asignSubReqDto.getTraineeId())
                .batchId(asignSubReqDto.getBatchId())
                .submissionDate(asignSubReqDto.getSubmissionDate())
                .submissionFile(asignSubReqDto.getSubmissionFile())
                .build();
    }
    public static AsignSubResDto assignmentSubEntityToDto(AssignmentSubEntity assignmentSubEntity, AssignmentEntity assignment, TraineeEntity trainee){
        return AsignSubResDto.builder()
                .asgSubId(assignmentSubEntity.getAsgSubId())
                .assignmentId(assignmentSubEntity.getAssignmentId())
                .assignmentName(assignment.getAssignmentName())
                .traineeId(assignmentSubEntity.getTraineeId())
                .fullName(trainee.getUser().getFullName())
                .profilePicture(trainee.getUser().getProfilePicture())
                .submissionDate(assignmentSubEntity.getSubmissionDate())
                .submissionFile(assignmentSubEntity.getSubmissionFile())
                .batchId(assignmentSubEntity.getBatchId())
                .evolution(assignmentSubEntity.getEvolution())
                .build();
    }
}
