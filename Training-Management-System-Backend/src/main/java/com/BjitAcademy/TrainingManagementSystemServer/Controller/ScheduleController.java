package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Assignment.*;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.ScheduleRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    @PostMapping("/api/schedule/{scheduleId}/add-assignment")
    private ResponseEntity<Object> addAssignmentToSchedule(@PathVariable Long scheduleId,@RequestBody AssignmentReqDto assignmentReqDto){
        return scheduleService.addAssignmentToSchedule(scheduleId,assignmentReqDto);
    }
    @GetMapping("/api/schedule/{trainerId}")
    private ResponseEntity<Object> getAllScheduleForTrainer(@PathVariable Long trainerId){
        return scheduleService.getAllScheduleForTrainer(trainerId);
    }
}
