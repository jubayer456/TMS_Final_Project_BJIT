package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.*;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.AssignmentMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ScheduleServiceImp implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AssignmentRepository assignmentRepository;
    @Override
    public ResponseEntity<Object> addAssignmentToSchedule(Long scheduleId,AssignmentReqDto assignmentReqDto) {
        //convert assignment request dto to assignment entity dto
        AssignmentEntity assignment= AssignmentMappingModel.assignmentDtoToEntity(assignmentReqDto);
        // checking schedule is already exist or not?
        ScheduleEntity schedule=scheduleRepository.findByScheduleId(assignmentReqDto.getScheduleId());
        if (schedule==null){
            throw new ScheduleNotFoundException("Schedule Not found");
        }
        //adding batch id in assignment so that specific trainee can see their assignment
        assignment.setBatchId(schedule.getBatchId());
        //adding assignment in schedule assignment list
        schedule.getAssignments().add(assignmentRepository.save(assignment));
        //save the schedule in schedule repository
        scheduleRepository.save(schedule);
        //showing success msg in UI schedule using status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully assignment creation")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}
