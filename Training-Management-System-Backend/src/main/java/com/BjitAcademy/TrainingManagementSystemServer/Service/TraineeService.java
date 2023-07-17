package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TraineeService {
    ResponseEntity<Object> createTrainee(TraineeRegReqDto traineeReqDto);

    ResponseEntity<List<TraineeResDto>> getAllTrainee();

    ResponseEntity<String> updateTrainee(TraineeRegReqDto traineeReqDto);

    ResponseEntity<String> deleteTrainee(Long traineeId);
}