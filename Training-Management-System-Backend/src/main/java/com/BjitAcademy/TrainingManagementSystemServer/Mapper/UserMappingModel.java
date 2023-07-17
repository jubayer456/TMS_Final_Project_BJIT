package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainee.TraineeRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Trainer.TrainerRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMappingModel {
    public static UserResDto userEntityToResDto(UserEntity user){
        return UserResDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .gender(user.getGender())
                .contactNumber(user.getContactNumber())
                .role(user.getRole())
                .build();
    }
}
