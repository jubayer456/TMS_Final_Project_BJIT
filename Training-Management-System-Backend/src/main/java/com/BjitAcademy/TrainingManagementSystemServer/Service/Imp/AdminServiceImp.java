package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.*;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.*;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.AdminMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.UserMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.*;
import com.BjitAcademy.TrainingManagementSystemServer.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public ResponseEntity<Object> createAdmin(AdminRegReqDto adminRegReqDto) {
        UserEntity userEntityById = userRepository.findByUserId(adminRegReqDto.getAdminId());
        if (userEntityById != null) {
            throw new UserException("Admin is Already taken. Please enter a new trainee Id");
        }
        UserEntity userEntityByEmail = userRepository.findByEmail(adminRegReqDto.getEmail());
        if (userEntityByEmail != null) {
            throw new UserException("Admin Already Exist.. Please Change the email");
        }

        // admin Registration Dto have some filed which is insert to user table,,,
        UserEntity user =  UserEntity.builder()
                .userId(adminRegReqDto.getAdminId())
                .fullName(adminRegReqDto.getFullName())
                .email(adminRegReqDto.getEmail())
                .password(passwordEncoder.encode(adminRegReqDto.getPassword()))
                .gender(adminRegReqDto.getGender())
                .profilePicture(adminRegReqDto.getProfilePicture())
                .contactNumber(adminRegReqDto.getContactNumber())
                .role(adminRegReqDto.getRole())
                .build();

        //convert admin dto to entity using admin mapping model
        AdminEntity admin = AdminMappingModel.AdminDtoToEntity(adminRegReqDto, user);
        adminRepository.save(admin);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("SuccessFully Registered")
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<Object> updateAdmin(@PathVariable Long adminId, AdminRegReqDto adminRegReqDto) {
        UserEntity admin = userRepository.findByUserId(adminId);
        if (admin == null) {
            throw new UserException("Admin is not found for update");
        }
        //set Al the properties without adminId for update
        admin.setEmail(adminRegReqDto.getEmail());
        admin.setFullName(adminRegReqDto.getFullName());
        admin.setContactNumber(adminRegReqDto.getContactNumber());
        userRepository.save(admin);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("SuccessFully Updated")
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAdminDetails(Long adminId) {
        UserEntity admin = userRepository.findByUserId(adminId);
        if (admin==null){
            throw new UserException("Admin Found");
        }
        UserResDto adminResDto=UserMappingModel.userEntityToResDto(admin);
        return new ResponseEntity<>(adminResDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResDto>> getAllUser() {
        List<UserEntity> users=userRepository.findAll();
        //Convert the user entity to user response Dto using mapping method
        List<UserResDto> userResList=users.stream().map(UserMappingModel::userEntityToResDto).toList();
        return new ResponseEntity<>(userResList,HttpStatus.OK);
    }
}
