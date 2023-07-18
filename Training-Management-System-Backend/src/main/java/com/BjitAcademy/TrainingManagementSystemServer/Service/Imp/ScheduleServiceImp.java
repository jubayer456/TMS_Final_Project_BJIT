package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.*;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.AssignmentMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.ScheduleMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ScheduleServiceImp implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TrainerRepository trainerRepository;
    private final AssignmentRepository assignmentRepository;
    private final TraineeRepository traineeRepository;
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
    @Override
    public ResponseEntity<Object> getAllScheduleForTrainer(Long trainerId) {
        TrainerEntity trainer=trainerRepository.findByTrainerId(trainerId);
        if (trainer==null){
            throw new TrainerNotFoundException("Trainer Not found for checking All schedule");
        }
        //search schedules using trainer id and convert schedule entity to schedule response dto for UI
        List<ScheduleResDto> trainerSchedules=scheduleRepository.findByTrainerId(trainerId).stream().map(ScheduleMappingModel::scheduleEntityToDto).toList();
        return new ResponseEntity<>(trainerSchedules,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateAssignment(Long assignmentId,AssignmentReqDto assignmentReqDto) {
        AssignmentEntity assignment=assignmentRepository.findByAssignmentId(assignmentId);
        if (assignment==null){
            throw new AssignmentNotFoundException("Assignment are not found for update");
        }
        //using set method for updating assignment data
        assignment.setAssignmentFile(assignmentReqDto.getAssignmentFile());
        assignment.setAssignmentName(assignmentReqDto.getAssignmentName());
        assignment.setDeadLine(assignmentReqDto.getDeadLine());
        //save the assignment entity in assignment repository
        assignmentRepository.save(assignment);
        //showing success msg in UI schedule using status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully updated")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeAssignment(Long assignmentId) {
        AssignmentEntity assignment=assignmentRepository.findByAssignmentId(assignmentId);
        if (assignment==null){
            throw new AssignmentNotFoundException("Assignment are not found for delete");
        }
        //find schedule entity using scheduleId ,,,then get the assignment list and remove the assignment from the list
        scheduleRepository.findByScheduleId(assignment.getScheduleId()).getAssignments().remove(assignment);
        //delete the assignment from the assignment repository
        assignmentRepository.delete(assignment);
        //showing success msg in UI schedule using status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully deleted")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addAssignmentSubmission(AsignSubReqDto asignSubReqDto) {
        AssignmentEntity assignment=assignmentRepository.findByAssignmentId(asignSubReqDto.getAssignmentId());
        if (assignment==null){
            throw new AssignmentNotFoundException("Assignment are not found for submission");
        }
//        AssignmentSubEntity assignmentSubEntity=assignmentSubRepository.findByTraineeId(asignSubReqDto.getTraineeId());
//        if (assignmentSubEntity!=null){
//            throw new TraineeAlreadyExistException("Trainee Already Submit their Assignment");
//        }
        // assignment submission req dto to assignment submission entity using mapper class
        AssignmentSubEntity assignmentSub=AssignmentMappingModel.assignmentSubDtoToEntity(asignSubReqDto);
        //add assignment sub to assignment submission list
        assignment.getAssignmentSubEntities().add(assignmentSub);
        //save assignment list to assignment assignmentRepository
        assignmentRepository.save(assignment);
        //showing success msg in UI schedule using status code
        SuccessResponseDto success=SuccessResponseDto.builder()
                .msg("Successfully submitted assignment")
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<AssignmentResDto>> getAllAssignment(Long scheduleId) {
        //checking, schedule is presented or not?
        ScheduleEntity schedule=scheduleRepository.findByScheduleId(scheduleId);
        if (schedule==null){
            throw new ScheduleNotFoundException("Schedule Not found");
        }
        //in schedule there is assignment list ,,, then using map function to convert entity to dto for UI
        Set<AssignmentResDto> assignments=schedule.getAssignments().stream().map(AssignmentMappingModel::assignmentEntityToDto).collect(Collectors.toSet());
        return new ResponseEntity<>(assignments,HttpStatus.OK);
    }
}
