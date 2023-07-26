package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.ScheduleRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    @PostMapping("/api/schedules/{scheduleId}/add-assignment")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> addAssignmentToSchedule(@PathVariable Long scheduleId,@RequestBody AssignmentReqDto assignmentReqDto){
        return scheduleService.addAssignmentToSchedule(scheduleId,assignmentReqDto);
    }
    @GetMapping("/api/schedules/{trainerId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> getAllScheduleForTrainer(@PathVariable Long trainerId){
        return scheduleService.getAllScheduleForTrainer(trainerId);
    }
    @PutMapping("/api/schedules/{assignmentId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> updateAssignment(@PathVariable Long assignmentId,@RequestBody AssignmentReqDto assignmentReqDto){
        return scheduleService.updateAssignment(assignmentId,assignmentReqDto);
    }
    @DeleteMapping("/api/schedules/{assignmentId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> removeAssignment(@PathVariable Long assignmentId){
        return scheduleService.removeAssignment(assignmentId);
    }
    @PostMapping("/api/schedules/add-assignmentSub")
    @PreAuthorize("hasRole('TRAINEE')")
    private ResponseEntity<Object> addAssignmentSubmission(@RequestBody AsignSubReqDto asignSubReqDto){
        return scheduleService.addAssignmentSubmission(asignSubReqDto);
    }

    @GetMapping("/api/schedules/{scheduleId}/assignments")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Set<AssignmentResDto>> getAllAssignment(@PathVariable Long scheduleId){
        return scheduleService.getAllAssignment(scheduleId);
    }
    @GetMapping("/api/schedules/{scheduleId}/{assignmentId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Set<AsignSubResDto>> getAllAssignmentSub(@PathVariable Long scheduleId,@PathVariable Long assignmentId){
        return scheduleService.getAllAssignmentSub(scheduleId,assignmentId);
    }

    @GetMapping("/api/schedules/{batchId}/assignmentSubmissions")
    @PreAuthorize("hasRole('TRAINEE')")
    private ResponseEntity<Set<AssignmentResDto>> getAllAssignmentForBatch(@PathVariable Long batchId){
        return scheduleService.getAllAssignmentForBatch(batchId);
    }
    @PutMapping("/api/schedules/{assignmentId}/{submissionId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> giveEvolution(@PathVariable Long assignmentId, @PathVariable Long submissionId, @RequestBody AssignmentEvoReqDto assignmentEvoReqDto){
        return scheduleService.giveEvolution(assignmentId,submissionId,assignmentEvoReqDto);
    }
}
