package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TrainerException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.TrainerMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.CourseRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.ScheduleRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.TrainerRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.UserRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TrainerServiceImp implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ScheduleRepository scheduleRepository;
    private final CourseRepository courseRepository;
    @Override
    @Transactional
    public ResponseEntity<Object> createTrainers(TrainerRegReqDto trainerRegReqDto) {
        UserEntity userEntityById=userRepository.findByUserId(trainerRegReqDto.getTrainerId());
        if (userEntityById!=null){
            throw new UserException("Trainer is Already taken. Please enter a new trainee Id");
        }
        UserEntity userEntityByEmail=userRepository.findByEmail(trainerRegReqDto.getEmail());
        if(userEntityByEmail!=null){
            throw new UserException("Trainer Already Exist.. Please Change the email");
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
    @Transactional
    public ResponseEntity<Object> updateTrainers(TrainerRegReqDto trainerRegReqDto) {
        UserEntity user = userRepository.findByUserId(trainerRegReqDto.getTrainerId());
        if (user == null) {
            throw new UserException("trainer is not found for update");
        }
        //update trainer details using set method
        user.setEmail(trainerRegReqDto.getEmail());
        user.setFullName(trainerRegReqDto.getFullName());
        user.setContactNumber(trainerRegReqDto.getContactNumber());

        TrainerEntity trainer = trainerRepository.findByTrainerId(trainerRegReqDto.getTrainerId());
        trainer.setAddress(trainerRegReqDto.getAddress());
        trainer.setDesignation(trainerRegReqDto.getDesignation());
        trainer.setJoiningDate(trainerRegReqDto.getJoiningDate());
        trainer.setExpertises(trainerRegReqDto.getExpertises());
        trainer.setTotalYrsExp(trainerRegReqDto.getTotalYrsExp());
        trainer.setUser(user);
        trainerRepository.save(trainer);
        return new ResponseEntity<>("SuccessFully Updated",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTrainer(Long trainerId) {
        TrainerEntity trainer = trainerRepository.findByTrainerId(trainerId);
        if (trainer==null){
            throw new UserException("trainer is not found for delete");
        }
        List<CourseEntity> course=courseRepository.findAll();
        boolean isTrainerAssociated= course.stream().anyMatch(courseEntity -> Objects.equals(courseEntity.getTrainer().getTrainerId(), trainerId));
        if (isTrainerAssociated){
            throw new TrainerException("trainer is Already exist in course");
        }
        trainerRepository.delete(trainer);
        return new ResponseEntity<>("SuccessFully Deleted Trainer",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> trainerDetails(Long trainerId) {
        TrainerEntity trainer = trainerRepository.findByTrainerId(trainerId);
        if (trainer==null){
            throw new UserException("trainer is not found");
        }
        TrainerResDto trainerResDto=TrainerMappingModel.trainerEntityToDto(trainer,trainer.getUser());
        return new ResponseEntity<>(trainerResDto,HttpStatus.OK);
    }
}
