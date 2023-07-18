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
}
