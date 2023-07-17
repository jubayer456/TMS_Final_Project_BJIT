package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin.AdminRegReqDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/api/auth/admin")
    public ResponseEntity<Object> createAdmin(@RequestBody AdminRegReqDto adminRegReqDto){
        return adminService.createAdmin(adminRegReqDto);
    }
    @PutMapping("/api/auth/admin")
    public ResponseEntity<Object> updateAdmin(@RequestBody AdminRegReqDto adminRegReqDto){
        return adminService.updateAdmin(adminRegReqDto);
    }
    @GetMapping("/api/admin/AllUser")
    public ResponseEntity<List<UserResDto>> getAllUser(){
        return adminService.getAllUser();
    }
}
