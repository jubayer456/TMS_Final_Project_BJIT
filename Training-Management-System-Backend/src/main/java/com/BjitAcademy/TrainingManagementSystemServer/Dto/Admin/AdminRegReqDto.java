package com.BjitAcademy.TrainingManagementSystemServer.Dto.Admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRegReqDto {
    private Long adminId;
    private String email;
    private String password;
    private String fullName;
    private String profilePicture;
    private String contactNumber;
    private String gender;
    private  final String role="ADMIN";
}
