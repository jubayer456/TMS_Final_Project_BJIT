package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchTraineeReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.BatchScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.BatchScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;
    @PostMapping("/api/batch-save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createBatch(@RequestBody BatchReqDto batchReqDto){
        return batchService.createBatch(batchReqDto);
    }
    @PutMapping("/api/batches/{batchId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateBatch(@PathVariable Long batchId, @RequestBody BatchReqDto batchReqDto){
        return batchService.updateBatch(batchId,batchReqDto);
    }
    @GetMapping("/api/batches")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BatchResDto>> getAllBatch(){
        return batchService.getAllBatch();
    }


    @PostMapping("/api/batches/add-trainee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addTraineeToBatch(@RequestBody BatchTraineeReqDto batchTraineeReqDto){
        return batchService.addTraineeToBatch(batchTraineeReqDto);
    }
    @DeleteMapping("/api/batches/trainees/{traineeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> removeTraineeFromBatch(@PathVariable Long traineeId){
        return batchService.removeTraineeFromBatch(traineeId);
    }
    @PostMapping("/api/batches/add-schedule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addScheduleToBatch(@RequestBody ScheduleReqDto scheduleReqDto){
        return batchService.addScheduleToBatch(scheduleReqDto);
    }
    @DeleteMapping("/api/batches/schedules/{scheduleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> removeScheduleFromBatch(@PathVariable Long scheduleId){
        return batchService.removeScheduleFromBatch(scheduleId);
    }
    @PutMapping("/api/batches/schedules/{scheduleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateScheduleFromBatch(@PathVariable Long scheduleId,@RequestBody ScheduleReqDto scheduleReqDto){
        return batchService.updateScheduleFromBatch(scheduleId,scheduleReqDto);
    }
    @GetMapping("/api/batches/{batchId}/schedules")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<BatchScheduleResDto>> getAllBatchSchedule(@PathVariable Long batchId){
        return batchService.getAllBatchSchedule(batchId);
    }
    @GetMapping("/api/batches/{batchId}/trainees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<TraineeResDto>> getAllTraineeBatch(@PathVariable Long batchId){
        return batchService.getAllTraineeBatch(batchId);
    }
}
