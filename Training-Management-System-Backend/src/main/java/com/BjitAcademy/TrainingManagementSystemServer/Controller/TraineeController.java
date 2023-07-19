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
    @PostMapping("/api/trainee")
    public ResponseEntity<Object> createTrainee(@RequestBody TraineeRegReqDto traineeReqDto) {
        return traineeService.createTrainee(traineeReqDto);
    }
    @PutMapping("/api/trainee")
    public ResponseEntity<Object> updateTrainee(@RequestBody TraineeRegReqDto traineeReqDto) {
        return traineeService.updateTrainee(traineeReqDto);
    }
    @GetMapping("/api/trainee/{traineeId}")
    public ResponseEntity<Object> traineeDetails(@PathVariable Long traineeId) {
        return traineeService.traineeDetails(traineeId);
    }
    @DeleteMapping("/api/trainee/{traineeId}")
    public ResponseEntity<Object> deleteTrainee(@PathVariable Long traineeId) {
        return traineeService.deleteTrainee(traineeId);
    }
    @GetMapping("/api/trainee/getAll")
    public ResponseEntity<List<TraineeResDto>> getAllTrainee() {
        return traineeService.getAllTrainee();
    }
}
