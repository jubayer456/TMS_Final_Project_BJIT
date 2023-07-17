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

    @Override
    public ResponseEntity<List<TrainerResDto>> getAllTrainer() {
        List<TrainerEntity> trainees=trainerRepository.findAll();

        //trainer entity to trainer res dto using mapping for user details collecting for showing frontend
        List<TrainerResDto> traineeResDtoList=trainees.stream().map(
                trainer-> TrainerMappingModel.trainerEntityToDto(trainer,trainer.getUser())).toList();
        return new ResponseEntity<>(traineeResDtoList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateTrainers(TrainerRegReqDto trainerRegReqDto) {
        UserEntity userEntityById = userRepository.findByUserId(trainerRegReqDto.getTrainerId());
        if (userEntityById == null) {
            throw new UserNotFoundException("trainer is not found for update");
        }
        //update trainer details using set method
        userEntityById.setEmail(trainerRegReqDto.getEmail());
        userEntityById.setFullName(trainerRegReqDto.getFullName());
        userEntityById.setGender(trainerRegReqDto.getGender());
        userEntityById.setProfilePicture(trainerRegReqDto.getProfilePicture());
        userEntityById.setContactNumber(trainerRegReqDto.getContactNumber());

        TrainerEntity trainer = trainerRepository.findByTrainerId(trainerRegReqDto.getTrainerId());
        trainer.setAddress(trainerRegReqDto.getAddress());
        trainer.setDesignation(trainerRegReqDto.getDesignation());
        trainer.setJoiningDate(trainerRegReqDto.getJoiningDate());
        trainer.setExpertises(trainerRegReqDto.getExpertises());
        trainer.setTotalYrsExp(trainerRegReqDto.getTotalYrsExp());
        trainer.setUser(userEntityById);
        trainerRepository.save(trainer);
        return new ResponseEntity<>("SuccessFully Updated",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTrainer(Long trainerId) {
        TrainerEntity trainer = trainerRepository.findByTrainerId(trainerId);
        if (trainer==null){
            throw new UserNotFoundException("trainer is not found for delete");
        }
        trainerRepository.delete(trainer);
        return new ResponseEntity<>("SuccessFully Deleted Trainer",HttpStatus.OK);
    }
}
