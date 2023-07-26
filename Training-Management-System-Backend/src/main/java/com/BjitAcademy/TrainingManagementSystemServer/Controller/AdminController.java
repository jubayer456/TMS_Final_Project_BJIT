package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/api/admin-save")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createAdmin(@RequestBody AdminRegReqDto adminRegReqDto){
        return adminService.createAdmin(adminRegReqDto);
    }
    @PutMapping("/api/admins/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateAdmin(@PathVariable Long adminId,@RequestBody AdminRegReqDto adminRegReqDto){
        return adminService.updateAdmin(adminId,adminRegReqDto);
    }

    @GetMapping("/api/admins/{adminId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAdminDetails(@PathVariable Long adminId){
        return adminService.getAdminDetails(adminId);
    }
    @GetMapping("/api/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResDto>> getAllUser(){
        return adminService.getAllUser();
    }
}
