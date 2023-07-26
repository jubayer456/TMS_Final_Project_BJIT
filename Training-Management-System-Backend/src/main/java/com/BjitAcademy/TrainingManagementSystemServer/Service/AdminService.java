package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AdminService {
    ResponseEntity<Object> createAdmin(AdminRegReqDto adminRegReqDto);

    ResponseEntity<List<UserResDto>> getAllUser();

    ResponseEntity<Object> updateAdmin(@PathVariable Long adminId, AdminRegReqDto adminRegReqDto);

    ResponseEntity<Object> getAdminDetails(Long adminId);
}
