package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TraineeEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.TraineeException;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.TraineeMappingModel;
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
            throw new UserException("TraineeId is Already taken. Please enter a new trainee Id");
        }
        UserEntity userEntityByEmail=userRepository.findByEmail(traineeRegReqDto.getEmail());
        if(userEntityByEmail!=null){
            throw new UserException("User Already Exist.. Please Change the email");
        }
        // trainee Registration Dto have some filed which is insert to user table,,,
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
        //creating success class for getting backend response msg to frontend
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
    public ResponseEntity<Object> updateTrainee(TraineeRegReqDto traineeReqDto) {
        UserEntity user = userRepository.findByUserId(traineeReqDto.getTraineeId());
        if (user == null) {
            throw new UserException("trainee is not found for update");
        }
        user.setEmail(traineeReqDto.getEmail());
        user.setFullName(traineeReqDto.getFullName());
        user.setContactNumber(traineeReqDto.getContactNumber());

        TraineeEntity trainee = traineeRepository.findByTraineeId(traineeReqDto.getTraineeId());
        trainee.setAddress(traineeReqDto.getAddress());
        trainee.setCgpa(traineeReqDto.getCgpa());
        trainee.setDob(traineeReqDto.getDob());
        trainee.setDegreeName(traineeReqDto.getDegreeName());
        trainee.setEducationalInstitute(traineeReqDto.getEducationalInstitute());
        trainee.setPassingYear(traineeReqDto.getPassingYear());
        trainee.setUser(user);
        traineeRepository.save(trainee);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("Successfully updated")
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTrainee(Long traineeId) {
        TraineeEntity trainee = traineeRepository.findByTraineeId(traineeId);
        if (trainee==null){
            throw new TraineeException("Trainee Not Found for delete");
        }
        traineeRepository.delete(trainee);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> traineeDetails(Long traineeId) {
        TraineeEntity trainee = traineeRepository.findByTraineeId(traineeId);
        if (trainee == null) {
            throw new UserException("trainee is not found for details");
        }
        TraineeResDto traineeResDto=TraineeMappingModel.traineeEntityToDto(trainee,trainee.getUser());
        return new ResponseEntity<>(traineeResDto,HttpStatus.OK);
    }
}
