package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<Object> createAdmin(AdminRegReqDto adminRegReqDto);
    ResponseEntity<List<UserResDto>> getAllUser();

    ResponseEntity<Object> updateAdmin(AdminRegReqDto adminRegReqDto);
}
