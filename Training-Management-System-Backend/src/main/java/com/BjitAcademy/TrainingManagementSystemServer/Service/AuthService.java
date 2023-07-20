package com.BjitAcademy.TrainingManagementSystemServer.Service;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.AuthenticationResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.LoginDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthenticationResDto> login(LoginDto loginDto);

    ResponseEntity<Object> updateUserPicture(Long userId, String picture);

    ResponseEntity<Object> userDetails(Long userId);
}
