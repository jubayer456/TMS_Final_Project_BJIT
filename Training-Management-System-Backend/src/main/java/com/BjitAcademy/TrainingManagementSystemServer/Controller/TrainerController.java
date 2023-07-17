package com.BjitAcademy.TrainingManagementSystemServer.Controller;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;
    @PostMapping("/api/auth/trainer")
    public ResponseEntity<Object> createTrainers(@RequestBody TrainerRegReqDto trainerRegReqDto){
        return trainerService.createTrainers(trainerRegReqDto);
    }
    @PutMapping("/api/auth/trainer")
    public ResponseEntity<Object> updateTrainers(@RequestBody TrainerRegReqDto trainerRegReqDto){
        return trainerService.updateTrainers(trainerRegReqDto);
    }
    @DeleteMapping("/api/trainer/{trainerId}")
    public ResponseEntity<Object> deleteTrainer(@PathVariable Long trainerId){
        return trainerService.deleteTrainer(trainerId);
    }
}
