package com.BjitAcademy.TrainingManagementSystemServer.Controller;

import com.BjitAcademy.TrainingManagementSystemServer.Service.AuthService;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.AuthenticationResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AuthController {
    private final AuthService authService;
    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthenticationResDto> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
    @PutMapping("/api/auth/updatePicture/{userId}")
    public ResponseEntity<Object> updateUserPicture(@PathVariable Long userId,@RequestBody String picture){
        return authService.updateUserPicture(userId,picture);
    }
    @GetMapping ("/api/auth/{userId}")
    public ResponseEntity<Object> userDetails(@PathVariable Long userId){
        return authService.userDetails(userId);
    }
}
