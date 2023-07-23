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
    @PostMapping("/api/schedule/{scheduleId}/add-assignment")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> addAssignmentToSchedule(@PathVariable Long scheduleId,@RequestBody AssignmentReqDto assignmentReqDto){
        return scheduleService.addAssignmentToSchedule(scheduleId,assignmentReqDto);
    }
    @GetMapping("/api/schedule/{trainerId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> getAllScheduleForTrainer(@PathVariable Long trainerId){
        return scheduleService.getAllScheduleForTrainer(trainerId);
    }
    @PutMapping("/api/schedule/{assignmentId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> updateAssignment(@PathVariable Long assignmentId,@RequestBody AssignmentReqDto assignmentReqDto){
        return scheduleService.updateAssignment(assignmentId,assignmentReqDto);
    }
    @DeleteMapping("/api/schedule/{assignmentId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> removeAssignment(@PathVariable Long assignmentId){
        return scheduleService.removeAssignment(assignmentId);
    }
    @PostMapping("/api/schedule/add-assignmentSub")
    @PreAuthorize("hasRole('TRAINEE')")
    private ResponseEntity<Object> addAssignmentSubmission(@RequestBody AsignSubReqDto asignSubReqDto){
        return scheduleService.addAssignmentSubmission(asignSubReqDto);
    }

    @GetMapping("/api/schedule/{scheduleId}/allAssignment")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Set<AssignmentResDto>> getAllAssignment(@PathVariable Long scheduleId){
        return scheduleService.getAllAssignment(scheduleId);
    }
    @GetMapping("/api/schedule/{scheduleId}/{assignmentId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Set<AsignSubResDto>> getAllAssignmentSub(@PathVariable Long scheduleId,@PathVariable Long assignmentId){
        return scheduleService.getAllAssignmentSub(scheduleId,assignmentId);
    }

    @GetMapping("/api/schedule/{batchId}/allAssignmentSub")
    @PreAuthorize("hasRole('TRAINEE')")
    private ResponseEntity<Set<AssignmentResDto>> getAllAssignmentForBatch(@PathVariable Long batchId){
        return scheduleService.getAllAssignmentForBatch(batchId);
    }
    @PutMapping("/api/schedule/{assignmentId}/{submissionId}")
    @PreAuthorize("hasRole('TRAINER')")
    private ResponseEntity<Object> giveEvolution(@PathVariable Long assignmentId, @PathVariable Long submissionId, @RequestBody AssignmentEvoReqDto assignmentEvoReqDto){
        return scheduleService.giveEvolution(assignmentId,submissionId,assignmentEvoReqDto);
    }
}
