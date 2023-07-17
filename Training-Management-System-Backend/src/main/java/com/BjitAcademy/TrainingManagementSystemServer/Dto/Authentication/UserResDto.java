package com.BjitAcademy.TrainingManagementSystemServer.Dto.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResDto {
    private Long userId;
    private String email;
    private String role;
    private String fullName;
    private String profilePicture;
    private String contactNumber;
    private String gender;
}
