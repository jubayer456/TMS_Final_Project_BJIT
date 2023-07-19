package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainerService {
    ResponseEntity<Object> createTrainers(TrainerRegReqDto trainerRegReqDto);

    ResponseEntity<List<TrainerResDto>> getAllTrainer();

    ResponseEntity<Object> updateTrainers(TrainerRegReqDto trainerRegReqDto);

    ResponseEntity<Object> deleteTrainer(Long trainerId);

    ResponseEntity<Object> trainerDetails(Long trainerId);
}
