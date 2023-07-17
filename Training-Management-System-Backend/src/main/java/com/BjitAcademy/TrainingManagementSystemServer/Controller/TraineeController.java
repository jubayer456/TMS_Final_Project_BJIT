package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TraineeController {
    private final TraineeService traineeService;
    @PostMapping("/api/auth/trainee")
    public ResponseEntity<Object> createTrainee(@RequestBody TraineeRegReqDto traineeReqDto) {
        return traineeService.createTrainee(traineeReqDto);
    }
    @PutMapping("/api/auth/trainee")
    public ResponseEntity<String> updateTrainee(@RequestBody TraineeRegReqDto traineeReqDto) {
        return traineeService.updateTrainee(traineeReqDto);
    }
}
