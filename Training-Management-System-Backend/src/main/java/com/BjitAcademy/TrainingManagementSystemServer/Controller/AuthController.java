package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Service.AuthService;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.AuthenticationResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AuthController {
    private final AuthService authServiceImp;
    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthenticationResDto> login(@RequestBody LoginDto loginDto) {
        return authServiceImp.login(loginDto);
    }
}
