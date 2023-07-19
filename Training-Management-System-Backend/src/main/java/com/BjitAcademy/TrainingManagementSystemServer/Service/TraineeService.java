package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TraineeService {
    ResponseEntity<Object> createTrainee(TraineeRegReqDto traineeReqDto);

    ResponseEntity<List<TraineeResDto>> getAllTrainee();

    ResponseEntity<Object> updateTrainee( TraineeRegReqDto traineeReqDto);

    ResponseEntity<Object> deleteTrainee(Long traineeId);

    ResponseEntity<Object> traineeDetails(Long traineeId);
}
