package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TraineeEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TraineeNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserAlreadyExistException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserNotFoundException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.TraineeMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.UserMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.TraineeRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.UserRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraineeServiceImp implements TraineeService {
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<Object> createTrainee(TraineeRegReqDto traineeRegReqDto) {
        UserEntity userEntityById=userRepository.findByUserId(traineeRegReqDto.getTraineeId());
        if (userEntityById!=null){
            throw new UserAlreadyExistException("TraineeId is Already taken. Please enter a new trainee Id");
        }
        UserEntity userEntityByEmail=userRepository.findByEmail(traineeRegReqDto.getEmail());
        if(userEntityByEmail!=null){
            throw new UserAlreadyExistException("User Already Exist.. Please Change the email");
        }

        UserEntity user = UserEntity.builder()
                .userId(traineeRegReqDto.getTraineeId())
                .fullName(traineeRegReqDto.getFullName())
                .email(traineeRegReqDto.getEmail())
                .password(passwordEncoder.encode(traineeRegReqDto.getPassword()))
                .gender(traineeRegReqDto.getGender())
                .contactNumber(traineeRegReqDto.getContactNumber())
                .profilePicture(traineeRegReqDto.getProfilePicture())
                .role(traineeRegReqDto.getRole())
                .build();
        TraineeEntity trainee= TraineeMappingModel.traineeDtoToEntity(traineeRegReqDto,user);
        traineeRepository.save(trainee);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("successfully registered")
                .build();
        return new ResponseEntity<>(success ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TraineeResDto>> getAllTrainee() {
        List<TraineeEntity> trainees=traineeRepository.findAll();
        List<TraineeResDto> traineeResDtoList=trainees.stream().map(trainee-> TraineeMappingModel.traineeEntityToDto(trainee,trainee.getUser())).toList();
        return new ResponseEntity<>(traineeResDtoList,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateTrainee(TraineeRegReqDto traineeReqDto) {
        UserEntity userEntityById = userRepository.findByUserId(traineeReqDto.getTraineeId());
        if (userEntityById == null) {
            throw new UserNotFoundException("trainee is not found for update");
        }
        userEntityById.setEmail(traineeReqDto.getEmail());
        userEntityById.setFullName(traineeReqDto.getFullName());
        userEntityById.setGender(traineeReqDto.getGender());
        userEntityById.setProfilePicture(traineeReqDto.getProfilePicture());
        userEntityById.setContactNumber(traineeReqDto.getContactNumber());

        TraineeEntity trainee = traineeRepository.findByTraineeId(traineeReqDto.getTraineeId());
        trainee.setAddress(traineeReqDto.getAddress());
        trainee.setCgpa(traineeReqDto.getCgpa());
        trainee.setDob(traineeReqDto.getDob());
        trainee.setDegreeName(traineeReqDto.getDegreeName());
        trainee.setEducationalInstitute(traineeReqDto.getEducationalInstitute());
        trainee.setPassingYear(traineeReqDto.getPassingYear());
        trainee.setUser(userEntityById);
        userRepository.save(userEntityById);
        return new ResponseEntity<>("SuccessFully Updated",HttpStatus.OK);
    }

}
