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
}
