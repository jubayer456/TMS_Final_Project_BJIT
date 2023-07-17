package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.TrainerEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;


public class TrainerMappingModel {
    public static TrainerEntity trainerDtoToEntity(TrainerRegReqDto trainerRegReqDto, UserEntity user){
        return TrainerEntity.builder()
                .trainerId(trainerRegReqDto.getTrainerId())
                .address(trainerRegReqDto.getAddress())
                .designation(trainerRegReqDto.getDesignation())
                .totalYrsExp(trainerRegReqDto.getTotalYrsExp())
                .expertises(trainerRegReqDto.getExpertises())
                .joiningDate(trainerRegReqDto.getJoiningDate())
                .user(user)
                .build();
    }
    public static TrainerResDto trainerEntityToDto(TrainerEntity trainerEntity, UserEntity user){
        return TrainerResDto.builder()
                .trainerId(trainerEntity.getTrainerId())
                .fullName(user.getFullName())
                .address(trainerEntity.getAddress())
                .gender(user.getGender())
                .contactNumber(user.getContactNumber())
                .profilePicture(user.getProfilePicture())
                .designation(trainerEntity.getDesignation())
                .totalYrsExp(trainerEntity.getTotalYrsExp())
                .expertises(trainerEntity.getExpertises())
                .joiningDate(trainerEntity.getJoiningDate())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
