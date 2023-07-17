package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TraineeEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TrainerEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserAlreadyExistException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.TrainerMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.UserMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.TrainerRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.UserRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrainerServiceImp implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<Object> createTrainers(TrainerRegReqDto trainerRegReqDto) {
        UserEntity userEntityById=userRepository.findByUserId(trainerRegReqDto.getTrainerId());
        if (userEntityById!=null){
            throw new UserAlreadyExistException("Trainer is Already taken. Please enter a new trainee Id");
        }
        UserEntity userEntityByEmail=userRepository.findByEmail(trainerRegReqDto.getEmail());
        if(userEntityByEmail!=null){
            throw new UserAlreadyExistException("Trainer Already Exist.. Please Change the email");
        }

        //trainer reg req to user entity then pass the user entity to mapper class
        UserEntity user =UserEntity.builder()
                .userId(trainerRegReqDto.getTrainerId())
                .fullName(trainerRegReqDto.getFullName())
                .email(trainerRegReqDto.getEmail())
                .password(passwordEncoder.encode(trainerRegReqDto.getPassword()))
                .gender(trainerRegReqDto.getGender())
                .profilePicture(trainerRegReqDto.getProfilePicture())
                .contactNumber(trainerRegReqDto.getContactNumber())
                .role(trainerRegReqDto.getRole())
                .build();

        // trainer req dto convert to trainer entity using mapper class named trainerMappingModel
        TrainerEntity trainer= TrainerMappingModel.trainerDtoToEntity(trainerRegReqDto,user);
        trainerRepository.save(trainer);
        return new ResponseEntity<>("successfully Registered", HttpStatus.OK);
    }
}
