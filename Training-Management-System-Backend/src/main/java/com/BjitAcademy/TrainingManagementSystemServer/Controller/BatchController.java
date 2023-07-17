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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;
    @PostMapping("/api/batch/save")
    public ResponseEntity<Object> createBatch(@RequestBody BatchReqDto batchReqDto){
        return batchService.createBatch(batchReqDto);
    }
    @PutMapping("/api/batch/{batchId}")
    public ResponseEntity<Object> updateBatch(@PathVariable Long batchId, @RequestBody BatchReqDto batchReqDto){
        return batchService.updateBatch(batchId,batchReqDto);
    }
}
