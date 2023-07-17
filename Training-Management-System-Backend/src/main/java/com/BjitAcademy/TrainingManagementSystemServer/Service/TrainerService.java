package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainerService {
    ResponseEntity<Object> createTrainers(TrainerRegReqDto trainerRegReqDto);

}
