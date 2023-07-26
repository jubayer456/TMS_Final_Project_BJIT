package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TraineeEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;


public class TraineeMappingModel {
    public static TraineeEntity traineeDtoToEntity(TraineeRegReqDto traineeRegReqDto, UserEntity user){
        return TraineeEntity.builder()
                .traineeId(traineeRegReqDto.getTraineeId())
                .dob(traineeRegReqDto.getDob())
                .cgpa(traineeRegReqDto.getCgpa())
                .address(traineeRegReqDto.getAddress())
                .educationalInstitute(traineeRegReqDto.getEducationalInstitute())
                .passingYear(traineeRegReqDto.getPassingYear())
                .degreeName(traineeRegReqDto.getDegreeName())
                .user(user)
                .build();
    }
    public static TraineeResDto traineeEntityToDto(TraineeEntity traineeEntity, UserEntity user){
        return TraineeResDto.builder()
                .traineeId(traineeEntity.getTraineeId())
                .fullName(user.getFullName())
                .dob(traineeEntity.getDob())
                .cgpa(traineeEntity.getCgpa())
                .address(traineeEntity.getAddress())
                .gender(user.getGender())
                .contactNumber(user.getContactNumber())
                .educationalInstitute(traineeEntity.getEducationalInstitute())
                .passingYear(traineeEntity.getPassingYear())
                .profilePicture(user.getProfilePicture())
                .degreeName(traineeEntity.getDegreeName())
                .email(user.getEmail())
                .batchId(traineeEntity.getBatchId())
                .role(user.getRole())
                .build();
    }
}
