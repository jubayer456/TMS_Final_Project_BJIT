package com.BjitAcademy.TrainingManagementSystemServer.Service.Imp;

import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.AuthenticationResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.LoginDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.SuccessResponseDto;
import com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication.UserResDto;
import com.BjitAcademy.TrainingManagementSystemServer.Entity.UserEntity;
import com.BjitAcademy.TrainingManagementSystemServer.Exception.UserException;
import com.BjitAcademy.TrainingManagementSystemServer.Mapper.UserMappingModel;
import com.BjitAcademy.TrainingManagementSystemServer.Repository.UserRepository;
import com.BjitAcademy.TrainingManagementSystemServer.Service.AuthService;
import com.BjitAcademy.TrainingManagementSystemServer.Utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public ResponseEntity<AuthenticationResDto> login(LoginDto loginDto) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()));
            var user=userRepository.findByEmail(loginDto.getEmail());
            var jwtToken=jwtService.generateToken(user);
            return new ResponseEntity<>(AuthenticationResDto.builder()
                    .token(jwtToken)
                    .user(UserMappingModel.userEntityToLoginResDto(user))
                    .build(), HttpStatus.OK);
        }

    @Override
    public ResponseEntity<Object> updateUserPicture(Long userId, String picture) {
        UserEntity user = userRepository.findByUserId(userId);
        if (user==null){
            throw new UserException("Admin Found");
        }
        user.setProfilePicture(picture);
        userRepository.save(user);
        SuccessResponseDto success=SuccessResponseDto.builder()
                .status(HttpStatus.OK.value())
                .msg("SuccessFully Updated Picture")
                .build();
        return new ResponseEntity<>(success,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> userDetails(Long userId) {
        UserEntity user = userRepository.findByUserId(userId);
        if (user==null){
            throw new UserException("User Not Found");
        }
        UserResDto userResDto=UserMappingModel.userEntityToResDto(user);
        return new ResponseEntity<>(userResDto,HttpStatus.OK);
    }
}
