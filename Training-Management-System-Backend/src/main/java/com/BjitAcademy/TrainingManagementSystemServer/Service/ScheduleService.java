package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ScheduleService {
    ResponseEntity<Object> addAssignmentToSchedule(Long scheduleId,AssignmentReqDto assignmentReqDto);

    ResponseEntity<Object> getAllScheduleForTrainer(Long trainerId);

    ResponseEntity<Object> updateAssignment(Long assignmentId,AssignmentReqDto assignmentReqDto);

    ResponseEntity<Object> removeAssignment(Long assignmentId);

    ResponseEntity<Object> addAssignmentSubmission(AsignSubReqDto asignSubReqDto);

    ResponseEntity<Set<AssignmentResDto>> getAllAssignment(Long scheduleId);

    ResponseEntity<Set<AsignSubResDto>> getAllAssignmentSub(Long scheduleId, Long assignmentId);

    ResponseEntity<Set<AssignmentResDto>> getAllAssignmentForBatch(Long batchId);

    ResponseEntity<Object> giveEvolution(Long assignmentId, Long submissionId, AssignmentEvoReqDto assignmentEvoReqDto);
}
