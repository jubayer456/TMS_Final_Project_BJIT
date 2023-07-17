package com.BjitAcademy.TrainingManagementSystemServer.Mapper;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminMappingModel {
    public static AdminEntity AdminDtoToEntity(AdminRegReqDto adminRegReqDto, UserEntity user){
        return AdminEntity.builder()
                .adminId(adminRegReqDto.getAdminId())
                .user(user)
                .build();
    }

    public static UserResDto UserEntityToDto(UserEntity userEntity){
        return UserResDto.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();
    }
}
