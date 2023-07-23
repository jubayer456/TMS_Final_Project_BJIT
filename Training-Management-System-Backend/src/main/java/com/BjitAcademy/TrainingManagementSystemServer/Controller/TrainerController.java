package com.BjitAcademy.TrainingManagementSystemServer.Controller;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;
    @PostMapping("/api/trainer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createTrainers(@RequestBody TrainerRegReqDto trainerRegReqDto){
        return trainerService.createTrainers(trainerRegReqDto);
    }
    @PutMapping("/api/trainer")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Object> updateTrainers(@RequestBody TrainerRegReqDto trainerRegReqDto){
        return trainerService.updateTrainers(trainerRegReqDto);
    }
    @DeleteMapping("/api/trainer/{trainerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteTrainer(@PathVariable Long trainerId){
        return trainerService.deleteTrainer(trainerId);
    }

    @GetMapping("/api/trainer/{trainerId}")
    @PreAuthorize("hasRole('TRAINER')")
    public ResponseEntity<Object> trainerDetails(@PathVariable Long trainerId){
        return trainerService.trainerDetails(trainerId);
    }
    @GetMapping("/api/trainer/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TrainerResDto>> getAllTrainer() {
        return trainerService.getAllTrainer();
    }
}
