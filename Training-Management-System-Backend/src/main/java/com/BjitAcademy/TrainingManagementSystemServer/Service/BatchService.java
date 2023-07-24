package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Batch.BatchTraineeReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.BatchScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.BatchScheduleResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Schedule.ScheduleReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface BatchService {

    ResponseEntity<Object> createBatch(BatchReqDto batchReqDto);

    ResponseEntity<Object> updateBatch(Long batchId, BatchReqDto batchReqDto);

    ResponseEntity<List<BatchResDto>> getAllBatch();

    ResponseEntity<Object> addTraineeToBatch(BatchTraineeReqDto batchTraineeReqDto);

    ResponseEntity<Object> removeTraineeFromBatch(Long traineeId);

    ResponseEntity<Object> addScheduleToBatch(ScheduleReqDto scheduleReqDto);
    ResponseEntity<Object> removeScheduleFromBatch(Long scheduleId);

    ResponseEntity<Set<BatchScheduleResDto>> getAllBatchSchedule(Long batchId);

    ResponseEntity<Set<TraineeResDto>> getAllTraineeBatch(Long batchId);


    ResponseEntity<Object> updateScheduleFromBatch(Long scheduleId, ScheduleReqDto scheduleReqDto);
}
